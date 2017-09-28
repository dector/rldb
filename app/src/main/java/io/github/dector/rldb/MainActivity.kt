package io.github.dector.rldb

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.controller_list.view.*
import kotlinx.android.synthetic.main.view_list_item.view.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        router = Conductor.attachRouter(this, root_container, savedInstanceState)
        if (!router.hasRootController())
            router.setRoot(RouterTransaction.with(ListController()))
    }

    override fun onBackPressed() {
        if (!router.handleBack())
            super.onBackPressed()
    }
}

class ListController : Controller() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup) =
            inflater.inflate(R.layout.controller_list, container, false).apply {
                list.setHasFixedSize(true)
                list.layoutManager = LinearLayoutManager(container.context)
                list.adapter = ListItemsAdapter(inflater).apply {
                    data = InMemoryGamesRepository().getAll().map {
                        ListItemViewModel(
                                title = it.name,
                                description = it.description,
                                imageUrl = it.imageUrl ?: "")
                    }
                }
            }
}

data class ListItemViewModel(val title: String, val description: String, val imageUrl: String)

@GlideModule
class CustomGlideModule : AppGlideModule()

class ListItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(vm: ListItemViewModel) {
        itemView.title.text = vm.title
        itemView.description.text = vm.description

        GlideApp.with(itemView)
                .load(vm.imageUrl)
                .centerCrop()
                .placeholder(R.drawable.placeholder_list_item_image)
                .into(itemView.image)

        itemView.setOnClickListener {
            Snackbar.make(itemView.rootView, "Selected: ${vm.title}", Snackbar.LENGTH_SHORT).show()
        }
    }
}

class ListItemsAdapter(private val inflater: LayoutInflater) : RecyclerView.Adapter<ListItemViewHolder>() {

    private val _data = mutableListOf<ListItemViewModel>()

    var data: List<ListItemViewModel>
        get() = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListItemViewHolder {
        val view = inflater.inflate(R.layout.view_list_item, parent, false)
        return ListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder?, position: Int) {
        holder?.bind(data[position])
    }

    override fun getItemCount() = data.size
}

class ItemDetailsController : Controller() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup) =
            inflater.inflate(R.layout.controller_item_details, container, false)
}

interface GamesRepository {

    fun getAll(): List<Game>
}

class InMemoryGamesRepository : GamesRepository {

    override fun getAll() = listOf(
            Game(
                    name = "Nethack",
                    description = "Nethack RL",
                    imageUrl = "https://i.imgur.com/GOIK6wG.png"
            ),
            Game(
                    name = "Brogue",
                    description = "Brogue RL",
                    imageUrl = "https://i.imgur.com/IOqX1YS.png"
            )
    )
}

data class Game(
        val name: String,
        val description: String,
        val imageUrl: String? = null,
        val codingLanguage: String = "",
        val platforms: List<Platform> = emptyList(),
        val lastVersion: String = "",
        val lastVersionDate: Date? = null)

enum class Platform {
    Windows, Linux, MacOS, Android, iOS
}