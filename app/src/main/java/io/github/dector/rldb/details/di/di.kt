package io.github.dector.rldb.details.di

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.Component
import dagger.Module
import dagger.Provides
import io.github.dector.rldb.R
import io.github.dector.rldb.common.di.NavigationModule
import io.github.dector.rldb.common.di.RepositoriesModule
import io.github.dector.rldb.common.domain.Game
import io.github.dector.rldb.common.repositories.GamesRepository
import io.github.dector.rldb.common.tools.GlideApp
import io.github.dector.rldb.details.view.controllers.ItemDetailsController
import kotlinx.android.synthetic.main.controller_item_details.view.*

@Module
class ItemDetailsModule(
        private val context: Context,
        private val itemUuid: String,
        // FIXME don't belong here
        private val viewInitializer: (Game, View) -> Unit,
        private val favouritesButtonAction: () -> Unit) {

    @Provides
    fun layoutInflater() = LayoutInflater.from(context)

    @Provides
    fun activity() = context as Activity

    @Provides
    fun rootView(activity: Activity): ViewGroup
            = activity.findViewById(R.id.root_container)

    @Provides
    fun viewBuilder(
            inflater: LayoutInflater,
            rootView: ViewGroup,
            repo: GamesRepository) = inflater.inflate(R.layout.controller_item_details, rootView, false).apply {
        val item = repo.byUuid(itemUuid) ?: return@apply

        title.text = item.name
        description.text = item.description

        viewInitializer(item, favouritesButton)

        GlideApp.with(rootView)
                .load(item.imageUrl)
                .centerCrop()
                .placeholder(R.drawable.placeholder_list_item_image)
                .into(image)

        favouritesButton.setOnClickListener { favouritesButtonAction() }
    }
}

@Component(modules = arrayOf(NavigationModule::class, ItemDetailsModule::class, RepositoriesModule::class))
interface ItemDetailsComponent {

    fun inject(controller: ItemDetailsController)
}