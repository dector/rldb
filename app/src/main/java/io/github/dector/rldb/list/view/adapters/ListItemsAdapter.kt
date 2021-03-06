package io.github.dector.rldb.list.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.dector.rldb.R
import io.github.dector.rldb.list.view.view_holders.ListItemViewHolder
import io.github.dector.rldb.list.viewmodels.ListItemViewModel


class ListItemsAdapter(
        private val inflater: LayoutInflater,
        private val onItemSelected: OnItemSelectedListener)
    : RecyclerView.Adapter<ListItemViewHolder>() {

    interface OnItemSelectedListener {

        fun onItemSelected(item: ListItemViewModel)
    }

    private val _data = mutableListOf<ListItemViewModel>()

    var data: List<ListItemViewModel>
        get() = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListItemViewHolder {
        val view = inflater.inflate(R.layout.view_list_item, parent, false)
        return ListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder?, position: Int) {
        holder?.bind(data[position], onItemSelected)
    }

    override fun getItemCount() = data.size
}