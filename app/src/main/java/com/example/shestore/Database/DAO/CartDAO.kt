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
    fun getCartProducts() : LiveData<List<CartEntity>>

    /** Checks the product exists in the cart_table
     * @return LiveData INT
     * */
    @Query("SELECT COUNT(*) FROM cart_table WHERE product_id = :p_id")
    fun checkProductExitsLD(p_id: Int) : LiveData<Int>

    /** Checks the product exists in the cart_table
     * @return  INT
     * */
    @Query("SELECT COUNT(*) FROM cart_table WHERE product_id = :p_id")
    fun checkProductExits(p_id: Int) : Int

    @Query("DELETE FROM cart_table WHERE product_id = :p_id")
    fun deleteItemFromCart(p_id: Int)

    @Query("SELECT product_id FROM cart_table")
    fun getCartProductIDs() : List<Int>
}