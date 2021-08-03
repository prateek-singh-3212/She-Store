package com.example.shestore.Database.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shestore.Database.Entity.WishlistEntity

@Dao
interface WishlistDAO {

    @Insert
    fun addToWishlist(wishlistData: WishlistEntity)

    /** Get all Cart data in desc time order*/
    @Query("SELECT * FROM wishlist_table ORDER BY time DESC")
    fun getWishListProducts() : LiveData<List<WishlistEntity>>

    /** Checks the product exists in the wishlist_table
     * @return LiveData INT
     * */
    @Query("SELECT COUNT(*) FROM wishlist_table WHERE product_id = :p_id")
    fun checkProductExitsLD(p_id: Int) : LiveData<Int>

    /** Checks the product exists in the wishlist_table
     * @return LiveData INT
     * */
    @Query("SELECT COUNT(*) FROM wishlist_table WHERE product_id = :p_id")
    fun checkProductExits(p_id: Int) : Int

    /**
     * Deletes the product of give id from wishlist.
     * @param p_id : ID of product which should be deleted
     * */
    @Query("DELETE FROM wishlist_table WHERE product_id = :p_id")
    fun deleteItemFromWishlist(p_id: Int)

    /** Returns the IDs of all product available in wishlist
     * @return List<Int>
     */
    @Query("SELECT product_id FROM wishlist_table")
    fun getWishListProductIDs() : List<Int>
}