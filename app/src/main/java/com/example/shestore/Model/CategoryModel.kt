package com.example.shestore.Model

data class CategoryModel(val categoryName : String, val categoryImageURL: String)

fun setCategoryData() : List<CategoryModel> {
    return listOf(
        CategoryModel(
            "Ethnic Wear",
            "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/13956806/2021/6/29/21706f6a-c4c8-45d9-b808-3e98fe3a02661624960530816-GoSriKi-Women-Black--Green-Yoke-Design-Kurta-With-Palazzos---1.jpg"
        ),
        CategoryModel(
            "Tops",
            "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/productimage/2021/6/19/37033945-63f6-4fca-9397-143ff38a38c61624094004740-1.jpg"
        ),
        CategoryModel(
            "Jewellery",
        "https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/productimage/2019/1/18/a562d5c6-be6c-4871-a064-a4c0c05aa8df1547817077168-1.jpg"
        ),
        CategoryModel(
            "Lipsticks",
            "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/productimage/2021/1/9/b0841404-fbd0-41bc-afcd-56eeacdec8e01610179137422-1.jpg"
        )
    )
}