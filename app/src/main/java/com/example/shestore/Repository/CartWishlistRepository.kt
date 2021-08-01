package com.example.shestore.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.shestore.Database.DAO.CartWishlistDAO
import com.example.shestore.Database.Database
import com.example.shestore.Database.Entity.CartWishlistEntity
import com.example.shestore.Utility.Coroutines

class CartWishlistRepository(context: Context) {

    private var cartWishlistDAO: CartWishlistDAO? = null
    private var cartProducts: LiveData<CartWishlistEntity>? = null
    private var wishlistProducts: LiveData<CartWishlistEntity>? = null

    init {
        val database: Database = Database.getInstance(context)
        this.cartWishlistDAO = database.cartWishListDAO()

        // Used this !! because already intitated this above
        cartProducts = cartWishlistDAO!!.getCartProducts()
        wishlistProducts = cartWishlistDAO!!.getCartProducts()
    }

    fun getCartProducts() : LiveData<CartWishlistEntity> = cartProducts!!

    fun getWishlistProducts() : LiveData<CartWishlistEntity> = wishlistProducts!!

    fun insetDataInDatabase(data: CartWishlistEntity) {
        Coroutines.launchDefault {
            cartWishlistDAO!!.insertProduct(data)
        }
    }
}