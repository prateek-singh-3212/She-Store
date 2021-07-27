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
            "Clothes",
            R.drawable.ic_deress,
            "products?category=32"
        ),
        TabModel(
            "Beauty",
            R.drawable.ic_beauty,
            "products?category=17"
        ),
        TabModel(
            "Gadgets",
            R.drawable.ic_gadget,
            "products?category=17"
        ),
        TabModel(
            "Jewellery",
            R.drawable.ic_jewellery,
            "products?category=17"
        ),
        TabModel(
            "Sports",
            R.drawable.ic_sports,
            "products?category=17"
        ),
        TabModel(
            "Bags",
            R.drawable.ic_bag,
            "products?category=33"
        ),
        TabModel(
            "Footwear",
            R.drawable.ic_shoes,
            "products?category=17"
        )
    )
}