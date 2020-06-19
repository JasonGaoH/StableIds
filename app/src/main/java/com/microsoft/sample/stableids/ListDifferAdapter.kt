package com.microsoft.sample.stableids

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * @see androidx.recyclerview.widget.AdapterListUpdateCallback
 */
class ListDifferAdapter : RecyclerView.Adapter<ListDifferAdapter.ViewHolder>() {

  var items: List<Item>
    set(value) = differ.submitList(value)
    get() = differ.currentList

  private val differ: AsyncListDiffer<Item> = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
      return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
      return oldItem == newItem
    }
  })

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = differ.currentList[position]
    holder.subjectView.text = item.subject
    holder.summaryView.text = item.summary
    Log.d("ListDifferAdapter", "onBindViewHolder: $position: ${item.subject} - ${item.summary}")
  }

  override fun getItemCount(): Int = differ.currentList.size

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val subjectView: TextView = itemView.findViewById(R.id.subject)
    val summaryView: TextView = itemView.findViewById(R.id.summary)
  }
}
