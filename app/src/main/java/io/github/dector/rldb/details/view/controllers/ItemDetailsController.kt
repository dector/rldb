package io.github.dector.rldb.details.view.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import io.github.dector.rldb.R
import io.github.dector.rldb.domain.Uuid
import io.github.dector.rldb.games_list.repositories.InMemoryGamesRepository
import io.github.dector.rldb.tools.GlideApp
import kotlinx.android.synthetic.main.controller_item_details.view.*


class ItemDetailsController(private val itemUuid: Uuid) : Controller() {

    constructor(state: Bundle) : this(state.getString("uuid", ""))

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup) =
            inflater.inflate(R.layout.controller_item_details, container, false).apply {
                val item = InMemoryGamesRepository().byUuid(itemUuid)

                title.text = item?.name
                description.text = item?.description

                GlideApp.with(container)
                        .load(item?.imageUrl)
                        .centerCrop()
                        .placeholder(R.drawable.placeholder_list_item_image)
                        .into(image)
            }
}