package io.github.dector.rldb.games_list.view.controllers

import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RouterTransaction
import io.github.dector.rldb.R
import io.github.dector.rldb.common.repositories.InMemoryGamesRepository
import io.github.dector.rldb.details.view.controllers.ItemDetailsController
import io.github.dector.rldb.domain.Uuid
import io.github.dector.rldb.favourites.view.controllers.FavouritesController
import io.github.dector.rldb.games_list.view.adapters.ListItemsAdapter
import io.github.dector.rldb.games_list.viewmodels.ListItemViewModel
import kotlinx.android.synthetic.main.controller_list.view.*


class ListController : Controller() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup) =
            inflater.inflate(R.layout.controller_list, container, false).apply {
                list.setHasFixedSize(true)
                list.layoutManager = LinearLayoutManager(container.context)
                list.adapter = ListItemsAdapter(inflater, { uuid -> openDetails(uuid) }).apply {
                    data = InMemoryGamesRepository().getAll().map {
                        ListItemViewModel(
                                uuid = it.uuid,
                                title = it.name,
                                description = it.description,
                                imageUrl = it.imageUrl ?: "")
                    }
                }
            }

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