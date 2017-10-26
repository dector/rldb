package io.github.dector.rldb.games_list.view.view_holders

import android.support.v7.widget.RecyclerView
import android.view.View
import io.github.dector.rldb.R
import io.github.dector.rldb.common.domain.Uuid
import io.github.dector.rldb.common.tools.GlideApp
import io.github.dector.rldb.games_list.viewmodels.ListItemViewModel
import kotlinx.android.synthetic.main.view_list_item.view.*


class ListItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(vm: ListItemViewModel, onItemSelected: (Uuid) -> Unit) {
        itemView.title.text = vm.title
        itemView.description.text = vm.description

        GlideApp.with(itemView)
                .load(vm.imageUrl)
                .centerCrop()
                .placeholder(R.drawable.placeholder_list_item_image)
                .into(itemView.image)

        itemView.setOnClickListener {
            onItemSelected(vm.uuid)
        }
    }
}