package com.example.shestore.Repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.shestore.Database.DAO.CartDAO
import com.example.shestore.Database.DAO.WishlistDAO
import com.example.shestore.Database.Database
import com.example.shestore.Database.Entity.CartEntity
import com.example.shestore.Database.Entity.WishlistEntity
import com.example.shestore.Interface.FeedbackListener
import com.example.shestore.Interface.FeedbackType
import com.example.shestore.Interface.ItemDetailStatus
import com.example.shestore.Model.WooCommerceItemsDetail
import com.example.shestore.Network.WooCommerceAPIClient
import com.example.shestore.Utility.Coroutines
import com.example.shestore.WooCommerce.ItemDetailAPI
import kotlinx.coroutines.cancel
import retrofit2.Response

class CartWishlistRepository(context: Context, val feedbackListener: FeedbackListener,val itemDetailStatusListener: ItemDetailStatus?) {

    private var wishlistDAO: WishlistDAO? = null
    private var cartDAO: CartDAO? = null
    private var cartProducts: LiveData<List<CartEntity>>? = null
    private var wishlistProducts: LiveData<WishlistEntity>? = null
    private var itemDetailAPI: ItemDetailAPI = ItemDetailAPI()

    init {
        val database: Database = Database.getInstance(context)
        this.wishlistDAO = database.WishListDAO()
        this.cartDAO = database.CartDAO()

        // Used this !! because already intitated this above
        cartProducts = cartDAO!!.getCartProducts()
        wishlistProducts = wishlistDAO!!.getWishListProducts()
    }

    fun getCartProducts() : LiveData<List<CartEntity>> = cartProducts!!

    fun getWishlistProducts() : LiveData<WishlistEntity> = wishlistProducts!!

    /** Add Data to wishlist table*/
    fun addToWishlist(data: WishlistEntity) {
        Coroutines.launchDefault {
            if (wishlistDAO!!.checkProductExits(data.product_id) == 0) {
                wishlistDAO!!.addToWishlist(data)
                feedbackListener.message(FeedbackType.WISHLIST,"${data.product_name} added to wishlist")
            } else {
                // If Product Exists then delete it from table
                Log.d("SQLITE", "DATA ALREADY EXISTS")
                wishlistDAO!!.deleteItemFromWishlist(data.product_id)
                feedbackListener.message(FeedbackType.WISHLIST,"${data.product_name} removed from wishlist")
            }
        }
    }

    /** Checks the Product exist in wishlist.
     * @return LiveData<Int>
     * */
    fun isProductInWishlist(p_id: Int) : LiveData<Int> = wishlistDAO!!.checkProductExitsLD(p_id)

    /**
     * Checks the product exist in cart.
     * @return LiveData<Int>
     * */
    fun isProductInCart(p_id: Int) : LiveData<Int> = cartDAO!!.checkProductExitsLD(p_id)

    /** Add Data to cart table*/
    fun addToCart(data: CartEntity) {
        Coroutines.launchDefault {
            if (cartDAO!!.checkProductExits(data.product_id) == 0) {
                cartDAO!!.addToCart(data)
                feedbackListener.message(FeedbackType.CART,"true")
            } else {
                // If Product Exists then delete it from table
                cartDAO!!.deleteItemFromCart(data.product_id)
                feedbackListener.message(FeedbackType.CART,"false")
            }
        }
    }

    /** This function returns all the product ids available in cart
     * TODO: Find why fetching live data does'nt require to run in coroutines*/
    fun getCartProductIDs() : List<Int> = cartDAO!!.getCartProductIDs()


    /** This function fetch id from database and returns all products detail from server*/
    fun fetchCartProductsFromServer() {
        Coroutines.launchIO {

            if (itemDetailStatusListener == null) {
                this.cancel("itemDetailStatusListener Not Found!!!")
                return@launchIO
            }

            itemDetailStatusListener.dataLoadingStatus(true)

            val ids = cartDAO!!.getCartProductIDs()

            val response: Response<List<WooCommerceItemsDetail>> = itemDetailAPI.getProductDetailFromIds(ids)

            if (response.isSuccessful) {
                itemDetailStatusListener.onLoadComplete(response.body()!!)
                itemDetailStatusListener.dataLoadingStatus(false)
            } else {
                itemDetailStatusListener.onErrorOccurred("Unable to Fetch Data Error Code: ${response.code()}")
                itemDetailStatusListener.dataLoadingStatus(false)
            }
        }
    }
}