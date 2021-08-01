package com.example.shestore.Database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

/**
 * All the products which is in wishlist is having CAT_TYPE = 0
 * All the products which is in cart is having CAT_TYPE = 1
 *
 * If their is no size then can use 0
 * */
@Entity(tableName = "cart_wishlist_table")
class CartWishlistEntity(
    @NotNull
    val product_id: Int,
    @NotNull
    val product_name: String,
    @NotNull
    val time_cart: Long,
    val status: String,
    val cat_type: Int,
    @NotNull
    val quantity: Int,
    @NotNull
    val size: String,
    val product_link: String
) {
    @PrimaryKey(autoGenerate = true)
    var s_no: Int = 0
}
//
///** Data is of wishlist or cart */
//enum class CatTypeDatabase