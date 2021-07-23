package com.example.shestore.Model

import androidx.annotation.DrawableRes
import com.example.shestore.R

data class TabModel(val catName: String, @DrawableRes val catImageResId: Int, val catItemRequestURL: String)

fun setTabData(): List<TabModel> {
    return listOf(
        TabModel(
            "Home",
            R.drawable.ic_home,
            "A"
        ),
        TabModel(
            "Clothes",
            R.drawable.ic_deress,
            "A"
        ),
        TabModel(
            "Beauty",
            R.drawable.ic_beauty,
            "A"
        ),
        TabModel(
            "Gadgets",
            R.drawable.ic_gadget,
            "A"
        ),
        TabModel(
            "Jewellery",
            R.drawable.ic_jewellery,
            "A"
        ),
        TabModel(
            "Sports",
            R.drawable.ic_sports,
            "A"
        ),
        TabModel(
            "Bags",
            R.drawable.ic_bag,
            "A"
        ),
        TabModel(
            "Footwear",
            R.drawable.ic_shoes,
            "A"
        )
    )
}