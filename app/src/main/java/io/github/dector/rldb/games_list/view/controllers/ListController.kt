package io.github.dector.rldb.games_list.view.controllers

import android.content.Context
import android.view.*
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RouterTransaction
import io.github.dector.rldb.R
import io.github.dector.rldb.details.view.controllers.ItemDetailsController
import io.github.dector.rldb.di.DaggerListControllerComponent
import io.github.dector.rldb.di.ListControllerModule
import io.github.dector.rldb.domain.Uuid
import io.github.dector.rldb.favourites.view.controllers.FavouritesController
import io.github.dector.rldb.games_list.view.adapters.ListItemsAdapter
import javax.inject.Inject
import javax.inject.Provider


class ListController : Controller() {

    @Inject lateinit var viewProvider: Provider<View>

    private val onItemSelectedListener = object : ListItemsAdapter.OnItemSelectedListener {

        override fun onItemSelected(uuid: Uuid) {
            openDetails(uuid)
        }
    }

    override fun onContextAvailable(context: Context) {
        // FIXME will be called again when context will be changed
        DaggerListControllerComponent.builder()
                .listControllerModule(ListControllerModule(context, onItemSelectedListener))
                .build()
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup) =
            viewProvider.get()

    private fun openDetails(uuid: Uuid) {
        router.pushController(RouterTransaction.with(ItemDetailsController(uuid)))
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favourites -> router.pushController(RouterTransaction.with(FavouritesController()))
            else -> return super.onOptionsItemSelected(item)
        }

        return true
    }
}