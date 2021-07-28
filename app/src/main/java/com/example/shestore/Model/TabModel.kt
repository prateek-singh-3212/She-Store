package com.example.shestore.Model

import androidx.annotation.DrawableRes
import com.example.shestore.R

data class TabModel(val catName: String, @DrawableRes val catImageResId: Int, val categoryEndpointURL: String)

fun setTabData(): List<TabModel> {
    return listOf(
        TabModel(
            "Home",
            R.drawable.ic_home,
            "products?category=17"
        ),
        TabModel(
            "Trending",
            R.drawable.ic_trending,
            "products?category=32"
        ),
        TabModel(
            "For You",
            R.drawable.ic_heart,
            "products?category=17"
        )
    )
}