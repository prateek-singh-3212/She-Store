package com.example.shestore.Database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "cart_table")
data class CartEntity(
    @NotNull
    val product_id: Int,
    @NotNull
    val product_name: String,
    @NotNull
    val time: Long,
    val status: String,
    @NotNull
    val quantity: Int,
    @NotNull
    val size: String,
    val product_link: String
) {
    @PrimaryKey(autoGenerate = true)
    var s_no: Int = 0
}