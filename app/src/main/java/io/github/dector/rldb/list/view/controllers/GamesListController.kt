package io.github.dector.rldb.list.view.controllers

import android.content.Context
import android.view.*
import com.bluelinelabs.conductor.Controller
import io.github.dector.rldb.R
import io.github.dector.rldb.common.di.NavigationModule
import io.github.dector.rldb.common.navigation.Navigation
import io.github.dector.rldb.common.tools.i
import io.github.dector.rldb.list.di.DaggerListControllerComponent
import io.github.dector.rldb.list.di.GamesListControllerModule
import io.github.dector.rldb.list.view.adapters.ListItemsAdapter
import io.github.dector.rldb.list.viewmodels.ListItemViewModel
import javax.inject.Inject
import javax.inject.Provider


class GamesListController : Controller() {

    @Inject lateinit var viewProvider: Provider<View>

    @Inject lateinit var navigation: Navigation

    private val onItemSelectedListener = object : ListItemsAdapter.OnItemSelectedListener {

        override fun onItemSelected(item: ListItemViewModel) {
            i { "Selected item: ${item.title}" }

            navigation.gotoDetails(item.uuid)
        }
    }

    override fun onContextAvailable(context: Context) {
        // FIXME will be called again when context will be changed
        DaggerListControllerComponent.builder()
                .navigationModule(NavigationModule(router))
                .gamesListControllerModule(GamesListControllerModule(context, onItemSelectedListener))
                .build()
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup) =
            viewProvider.get()

    override fun onAttach(view: View) {
        super.onAttach(view)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favourites -> navigation.gotoFavourites()
            else -> return super.onOptionsItemSelected(item)
        }

        return true
    }
}