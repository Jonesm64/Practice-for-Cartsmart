package com.example.infinitescrolltestbuttons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.infinitescrolltestbuttons.databinding.ItemInfiniteScrollBinding

class InfiniteScrollAdapter(
    private val onItemButtonClick: (itemNumber: Int, itemTitle: String, buttonName: String) -> Unit,
) : RecyclerView.Adapter<InfiniteScrollAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemInfiniteScrollBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemInfiniteScrollBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemNumber = position + 1
        val itemTitle = holder.itemView.context.getString(R.string.item_format, itemNumber)

        holder.binding.tvItemTitle.text = itemTitle

        holder.binding.btnItemLeft1.setOnClickListener {
            onItemButtonClick(itemNumber, itemTitle, "L1")
        }

        holder.binding.btnItemLeft2.setOnClickListener {
            onItemButtonClick(itemNumber, itemTitle, "L2")
        }

        holder.binding.btnItemRight1.setOnClickListener {
            onItemButtonClick(itemNumber, itemTitle, "R1")
        }

        holder.binding.btnItemRight2.setOnClickListener {
            onItemButtonClick(itemNumber, itemTitle, "R2")
        }

        holder.itemView.setOnClickListener {
            onItemButtonClick(itemNumber, itemTitle, "Row Item")
        }
    }

    override fun getItemCount(): Int = Int.MAX_VALUE
}
