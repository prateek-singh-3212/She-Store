package com.example.shestore.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shestore.Adapter.CartAdapter
import com.example.shestore.Model.setData
import com.example.shestore.R
import org.w3c.dom.Text

class ReviewOrder : Fragment() {

    private lateinit var finalItemRV: RecyclerView
    private lateinit var totalItemPrices: TextView
    private lateinit var deliveryCharge: TextView
    private lateinit var totalPrice: TextView
    private lateinit var couponDiscountPrice: TextView
    private lateinit var orderTotalPrice: TextView
    private lateinit var couponCode: EditText
    private lateinit var applyCoupon: Button
    private lateinit var placeOrder: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_review_order, container, false)
        setViews(view)

        // TODO: set the data of final products user is ordering in adapter
        finalItemRV.adapter = CartAdapter(setData())
        finalItemRV.layoutManager = LinearLayoutManager(requireContext())

        return view
    }

    private fun setViews(view: View) {
        finalItemRV = view.findViewById(R.id.review_order_product_detail_rv)
        totalItemPrices= view.findViewById(R.id.review_order_card_item_price)
        deliveryCharge= view.findViewById(R.id.review_order_card_delivery_price)
        totalPrice= view.findViewById(R.id.review_order_card_total_price)
        couponDiscountPrice= view.findViewById(R.id.review_order_card_coupon_price)
        orderTotalPrice= view.findViewById(R.id.review_order_card_order_total_price)
        couponCode = view.findViewById(R.id.review_order_coupon)
        applyCoupon = view.findViewById(R.id.review_order_apply_coupon)
        placeOrder = view.findViewById(R.id.review_order_place_order)
    }
}