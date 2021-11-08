package com.invillia.meubeats.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.invillia.meubeats.databinding.ProductListItemBinding
import com.invillia.meubeats.domain.model.Headphone

class ProductListAdapter :
    ListAdapter<Headphone, ProductListAdapter.ProductViewHolder>(DiffCallBack()) {

    inner class ProductViewHolder(private val binding: ProductListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Headphone) {
            val formattedPrice = "R$ ${item.price}"
            val formattedReview = "${item.totalReviews} Reviews"

            binding.apply {
                tvPhoneModel.text = item.model
                chipRating.text = item.rating.toString()
                tvNumberReviews.text = formattedReview
                tvPrice.text = formattedPrice
                Glide.with(root.context).load(item.image).into(ivPhoto)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductViewHolder(ProductListItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DiffCallBack : DiffUtil.ItemCallback<Headphone>() {
    override fun areItemsTheSame(oldItem: Headphone, newItem: Headphone) =
        oldItem.model == newItem.model

    override fun areContentsTheSame(oldItem: Headphone, newItem: Headphone) =
        oldItem == newItem
}
