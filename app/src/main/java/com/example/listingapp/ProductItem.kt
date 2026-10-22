package com.example.listingapp

data class ProductItem(
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String? = null
)
