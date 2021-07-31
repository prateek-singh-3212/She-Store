package com.example.shestore.Model

import androidx.annotation.DrawableRes
import com.example.shestore.R

data class TabModel(val catName: String, @DrawableRes val catImageResId: Int, val categoryEndpointURL: String)

fun setTabData(): List<TabModel> {
    return listOf(
        // Clothes
        TabModel(
            "Home",
            R.drawable.ic_home,
            "products?category=16"
        ),
        // Footwear
        TabModel(
            "Trending",
            R.drawable.ic_trending,
            "products?category=32"
        ),
        // Jewellery
        TabModel(
            "For You",
            R.drawable.ic_heart,
            "products?category=48"
        )
    )
}