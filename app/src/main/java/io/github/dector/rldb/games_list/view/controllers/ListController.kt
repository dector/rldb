package io.github.dector.rldb.games_list.view.controllers

import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import io.github.dector.rldb.R
import io.github.dector.rldb.games_list.repositories.InMemoryGamesRepository
import io.github.dector.rldb.games_list.view.adapters.ListItemsAdapter
import io.github.dector.rldb.games_list.viewmodels.ListItemViewModel
import kotlinx.android.synthetic.main.controller_list.view.*

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