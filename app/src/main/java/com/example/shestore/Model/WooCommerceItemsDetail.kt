package com.example.shestore.Model


data class WooCommerceItemsDetail (

    val id : Int,
    val name : String,
    val slug : String,
    val permalink : String,
    val date_created : String,
    val date_created_gmt : String,
    val date_modified : String,
    val date_modified_gmt : String,
    val type : String,
    val status : String,
    val featured : Boolean,
    val catalog_visibility : String,
    val description : String,
    val short_description : String,
    val sku : String,
    val price : Double,
    val regular_price : Double,
    val sale_price : String,
    val date_on_sale_from : String,
    val date_on_sale_from_gmt : String,
    val date_on_sale_to : String,
    val date_on_sale_to_gmt : String,
    val price_html : String,
    val on_sale : Boolean,
    val purchasable : Boolean,
    val total_sales : Int,
    val virtual : Boolean,
    val downloadable : Boolean,
    val downloads : List<String>,
    val download_limit : Int,
    val download_expiry : Int,
    val external_url : String,
    val button_text : String,
    val tax_status : String,
    val tax_class : String,
    val manage_stock : Boolean,
    val stock_quantity : String,
    val stock_status : String,
    val backorders : String,
    val backorders_allowed : Boolean,
    val backordered : Boolean,
    val sold_individually : Boolean,
    val weight : String,
    val dimensions : Dimensions,
    val shipping_required : Boolean,
    val shipping_taxable : Boolean,
    val shipping_class : String,
    val shipping_class_id : Int,
    val reviews_allowed : Boolean,
    val average_rating : Double,
    val rating_count : Int,
    val related_ids : List<Int>,
    val upsell_ids : List<String>,
    val cross_sell_ids : List<String>,
    val parent_id : Int,
    val purchase_note : String,
    val categories : List<Categories>,
    val tags : List<TagsDetail>,
    val images : List<Images>,
    val attributes : List<AttributeDetail>,
    val default_attributes : List<String>,
    val variations : List<String>,
    val grouped_products : List<String>,
    val menu_order : Int,
    val meta_data : List<MetaDataDetail>,
    val _links : _links
)

data class Categories (

    val id : Int,
    val name : String,
    val slug : String
)

data class Collection (

    val href : String
)

data class Dimensions (

    val length : String,
    val width : String,
    val height : String
)

data class Images (

    val id : Int,
    val date_created : String,
    val date_created_gmt : String,
    val date_modified : String,
    val date_modified_gmt : String,
    val src : String,
    val name : String,
    val alt : String
)

data class _links (

    val self : List<Self>,
    val collection : List<Collection>
)

data class Self (

    val href : String
)

data class TagsDetail (
    val id: Int,
    val name: String,
    val slug: String
)

data class AttributeDetail(
    val id: Int,
    val name: String,
    val position: Int,
    val visible: Boolean,
    val variation: Boolean,
    val options: Array<String>
)

data class MetaDataDetail(
    val id: Int,
    val key: String,
    val value: String
)