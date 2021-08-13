package com.example.shestore.Model

data class PostOrderModel (

    val payment_method : String,
    val payment_method_title : String,
    val set_paid : Boolean,
    val billingDataModel : BillingDataModel,
    val shippingDataModel : ShippingDataModel,
    val line_items : List<Line_items>,
    val shipping_lines : List<Shipping_lines>
)

data class BillingDataModel (

    val first_name : String,
    val last_name : String,
    val address_1 : String,
    val address_2 : String,
    val city : String,
    val state : String,
    val postcode : Int,
    val country : String,
    val email : String,
    val phone : String
)

data class Line_items (

    val product_id : Int,
    val quantity : Int
)

data class ShippingDataModel (

    val first_name : String,
    val last_name : String,
    val address_1 : String,
    val address_2 : String,
    val city : String,
    val state : String,
    val postcode : Int,
    val country : String
)

data class Shipping_lines (

    val method_id : String,
    val method_title : String,
    val total : Double
)