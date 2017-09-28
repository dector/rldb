package io.github.dector.rldb.details.view.controllers

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import io.github.dector.rldb.R
import io.github.dector.rldb.common.repositories.InMemoryGamesRepository
import io.github.dector.rldb.domain.Uuid
import io.github.dector.rldb.tools.GlideApp
import kotlinx.android.synthetic.main.controller_item_details.view.*


class ItemDetailsController(private val itemUuid: Uuid) : Controller() {

    constructor(state: Bundle) : this(state.getString("uuid", ""))

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup) =
            inflater.inflate(R.layout.controller_item_details, container, false).apply {
                val item = InMemoryGamesRepository().byUuid(itemUuid) ?: return@apply

                title.text = item.name
                description.text = item.description

                updateFavouriteButtonText(item.metaFavourite, view = favouritesButton)

                GlideApp.with(container)
                        .load(item.imageUrl)
                        .centerCrop()
                        .placeholder(R.drawable.placeholder_list_item_image)
                        .into(image)

                favouritesButton.setOnClickListener { onFavouriteAction() }
            }

    private fun onFavouriteAction() {
        val view = this.view ?: return

        val item = InMemoryGamesRepository().byUuid(itemUuid) ?: return

        InMemoryGamesRepository().toggleFavourite(item.uuid) { updatedItem ->
            updateFavouriteButtonText(updatedItem.metaFavourite)

            val messageText = if (updatedItem.metaFavourite) "Item added to favourites" else "Item removed from favourites"
            Snackbar.make(view, messageText, Snackbar.LENGTH_SHORT)
                    .show()
        }
    }

    private fun updateFavouriteButtonText(isFavourited: Boolean, view: View? = this.view) {
        val textResource = if (isFavourited)
            R.string.item_details_remove_from_favourites
        else R.string.item_details_add_to_favourites

        view?.favouritesButton?.text = view?.resources?.getString(textResource)
    }
}