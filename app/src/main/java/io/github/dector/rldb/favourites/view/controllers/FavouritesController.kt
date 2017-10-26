package io.github.dector.rldb.favourites.view.controllers

import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import io.github.dector.rldb.R
import io.github.dector.rldb.common.repositories.InMemoryGamesRepository
import io.github.dector.rldb.list.view.adapters.ListItemsAdapter
import io.github.dector.rldb.list.viewmodels.ListItemViewModel
import kotlinx.android.synthetic.main.controller_favourites.view.*


class FavouritesController : Controller() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup)
            = inflater.inflate(R.layout.controller_favourites, container, false).apply {
        list.setHasFixedSize(true)
        list.layoutManager = LinearLayoutManager(inflater.context)

        list.adapter = ListItemsAdapter(inflater, emptySelectedListener()).apply {
            data = InMemoryGamesRepository().getFavourite().map {
                ListItemViewModel(
                        uuid = it.uuid,
                        title = it.name,
                        description = it.description,
                        imageUrl = it.imageUrl ?: "")
            }
        }
    }

    private fun emptySelectedListener() = object : ListItemsAdapter.OnItemSelectedListener {

        override fun onItemSelected(item: ListItemViewModel) {}
    }
}