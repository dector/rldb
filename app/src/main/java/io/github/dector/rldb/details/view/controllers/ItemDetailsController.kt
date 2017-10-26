package io.github.dector.rldb.details.view.controllers

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import io.github.dector.rldb.R
import io.github.dector.rldb.common.di.RepositoriesModule
import io.github.dector.rldb.common.domain.Uuid
import io.github.dector.rldb.common.repositories.GamesRepository
import io.github.dector.rldb.details.di.DaggerItemDetailsComponent
import io.github.dector.rldb.details.di.ItemDetailsModule
import kotlinx.android.synthetic.main.controller_item_details.view.*
import javax.inject.Inject
import javax.inject.Provider


class ItemDetailsController(private val itemUuid: Uuid) : Controller() {

    constructor(state: Bundle) : this(state.getString("uuid", ""))

    @Inject lateinit var viewProvider: Provider<View>

    @Inject lateinit var repo: GamesRepository

    override fun onContextAvailable(context: Context) {
        DaggerItemDetailsComponent.builder()
                .repositoriesModule(RepositoriesModule())
                .itemDetailsModule(ItemDetailsModule(context, itemUuid,
                        { item, view -> updateFavouriteButtonText(item.metaFavourite, view = view) },
                        { onFavouriteAction() }))
                .build()
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup) =
            viewProvider.get()

    private fun onFavouriteAction() {
        val view = this.view ?: return

        val item = repo.byUuid(itemUuid) ?: return

        repo.toggleFavourite(item.uuid) { updatedItem ->
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