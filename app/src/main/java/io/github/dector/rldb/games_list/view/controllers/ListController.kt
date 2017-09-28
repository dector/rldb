package io.github.dector.rldb.games_list.view.controllers

import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RouterTransaction
import io.github.dector.rldb.R
import io.github.dector.rldb.common.repositories.InMemoryGamesRepository
import io.github.dector.rldb.details.view.controllers.ItemDetailsController
import io.github.dector.rldb.domain.Uuid
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
}