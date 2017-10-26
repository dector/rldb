package io.github.dector.rldb.list.di

import android.app.Activity
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.Component
import dagger.Module
import dagger.Provides
import io.github.dector.rldb.R
import io.github.dector.rldb.common.di.NavigationModule
import io.github.dector.rldb.common.repositories.InMemoryGamesRepository
import io.github.dector.rldb.list.view.adapters.ListItemsAdapter
import io.github.dector.rldb.list.view.controllers.GamesListController
import io.github.dector.rldb.list.view.view_holders.ListItemViewHolder
import io.github.dector.rldb.list.viewmodels.ListItemViewModel
import kotlinx.android.synthetic.main.controller_list.view.*


@Module
class GamesListControllerModule(
        private val context: Context,
        private val onListItemSelectedListener: ListItemsAdapter.OnItemSelectedListener) {

    @Provides fun layoutInflater(): LayoutInflater
            = LayoutInflater.from(context)

    @Provides fun layoutManager(): RecyclerView.LayoutManager
            = LinearLayoutManager(context)

    @Provides fun listAdapter(inflater: LayoutInflater): RecyclerView.Adapter<ListItemViewHolder> {
        return ListItemsAdapter(inflater, onListItemSelectedListener::onItemSelected).apply {
            data = InMemoryGamesRepository().getAll().map {
                ListItemViewModel(
                        uuid = it.uuid,
                        title = it.name,
                        description = it.description,
                        imageUrl = it.imageUrl ?: "")
            }
        }
    }

    @Provides fun viewBuilder(
            inflater: LayoutInflater,
            rootView: ViewGroup,
            layoutManager: RecyclerView.LayoutManager,
            listAdapter: RecyclerView.Adapter<ListItemViewHolder>): View {
        return inflater.inflate(R.layout.controller_list, rootView, false).apply {
            list.setHasFixedSize(true)
            list.layoutManager = layoutManager
            list.adapter = listAdapter
        }
    }

    @Provides fun activityRootView(activity: Activity): ViewGroup
            = activity.findViewById(R.id.root_container)

    // FIXME get rid of cast
    @Provides fun activity(): Activity
            = context as Activity
}

@Component(modules = arrayOf(NavigationModule::class, GamesListControllerModule::class))
interface ListControllerComponent {

    fun inject(controller: GamesListController)
}