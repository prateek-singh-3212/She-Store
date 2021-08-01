package com.example.shestore.Database.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shestore.Database.Entity.CartWishlistEntity

@Dao
interface CartWishlistDAO {

    @Insert
    fun insertProduct(cartWishlistData: CartWishlistEntity)

    /** Get all Cart data in desc time order*/
    @Query("SELECT * FROM cart_wishlist_table WHERE cat_type = 1 ORDER BY time_cart DESC")
    fun getCartProducts() : LiveData<CartWishlistEntity>

    /** Get all Cart data in desc time order*/
    @Query("SELECT * FROM cart_wishlist_table WHERE cat_type = 0 ORDER BY time_cart DESC")
    fun getWishListProducts() : LiveData<CartWishlistEntity>
}