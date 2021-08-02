package com.example.shestore.Database.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shestore.Database.Entity.CartEntity

@Dao
interface CartDAO {

    @Insert
    fun addToCart(cartData: CartEntity)

    /** Get all Cart data in desc time order*/
    @Query("SELECT * FROM cart_table ORDER BY time DESC")
    fun getCartProducts() : LiveData<CartEntity>
}