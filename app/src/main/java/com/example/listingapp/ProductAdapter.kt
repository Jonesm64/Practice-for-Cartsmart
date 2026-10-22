package com.example.listingapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.listingapp.databinding.ItemProductBinding

class ProductAdapter : ListAdapter<ProductItem, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductItem) {
            binding.tvProductName.text = item.name
            binding.tvDescription.text = item.description
            // Placeholder image is displayed via ImageView src in XML
        }
    }

    class ProductDiffCallback : DiffUtil.ItemCallback<ProductItem>() {
        override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean = oldItem == newItem
    }
}
