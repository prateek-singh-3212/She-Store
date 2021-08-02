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
@Entity(tableName = "wishlist_table")
class WishlistEntity(
    @NotNull
    val product_id: Int,
    @NotNull
    val product_name: String,
    @NotNull
    val time: Long,
    val status: String,
    val product_link: String
) {
    @PrimaryKey(autoGenerate = true)
    var s_no: Int = 0
}