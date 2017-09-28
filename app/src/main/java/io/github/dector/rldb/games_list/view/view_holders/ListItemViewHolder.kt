package io.github.dector.rldb.games_list.view.view_holders

import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.View
import io.github.dector.rldb.R
import io.github.dector.rldb.games_list.viewmodels.ListItemViewModel
import io.github.dector.rldb.tools.GlideApp
import kotlinx.android.synthetic.main.view_list_item.view.*

class ListItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(vm: ListItemViewModel) {
        itemView.title.text = vm.title
        itemView.description.text = vm.description

        GlideApp.with(itemView)
                .load(vm.imageUrl)
                .centerCrop()
                .placeholder(R.drawable.placeholder_list_item_image)
                .into(itemView.image)

        itemView.setOnClickListener {
            Snackbar.make(itemView.rootView, "Selected: ${vm.title}", Snackbar.LENGTH_SHORT).show()
        }
    }
}