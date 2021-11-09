package com.invillia.meubeats.presentation.adapter

import com.invillia.meubeats.domain.model.Headphone

class ProductClickHandler(private val clickListener: (headphone: Headphone) -> Unit) {
    fun onProductClick(item: Headphone) = clickListener(item)
}