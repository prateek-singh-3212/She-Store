package com.example.shestore.Model

data class itemList(val itemName: String?, val itemSubName: String?, val imageUrl: String?, val price: Double?) {
    constructor() : this(null , null, null, null)
}

fun setData() : List<itemList> {
    return listOf(
        itemList(
            "ALDO",
            "Dark Green Solid Structured Satchel",
            "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/14175098/2021/6/29/9c6db6de-33df-4266-8830-b6528d5611861624949166875-ALDO-Dark-Green-Solid-Structured-Satchel-5961624949166180-1.jpg",
            500.00
        ),
        itemList(
            "Athena",
            "Women Burgundy Solid Maxi Dress",
            "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/5559085/2018/5/9/11525849854374-Athena-Women-Burgundy-Solid-Maxi-Dress-4291525849853320-1.jpg",
            1000.00
        ),
        itemList(
            "Ring",
            "Silver-Toned & Blue Oxidised Classic Studs",
            "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/11476036/2020/3/10/58db6ea8-5842-4c1f-a3a9-379164d4e1c51583834519709-Rubans-Silver-Toned--Blue-Oxidised-Classic-Studs-62158383451-1.jpg",
            1500.00
        ),
        itemList(
            "Ring",
            "Silver-Toned & Blue Oxidised Classic Studs",
            "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/11476036/2020/3/10/58db6ea8-5842-4c1f-a3a9-379164d4e1c51583834519709-Rubans-Silver-Toned--Blue-Oxidised-Classic-Studs-62158383451-1.jpg",
            1500.00
        ),
        itemList(
            "Ring",
            "Silver-Toned & Blue Oxidised Classic Studs",
            "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/11476036/2020/3/10/58db6ea8-5842-4c1f-a3a9-379164d4e1c51583834519709-Rubans-Silver-Toned--Blue-Oxidised-Classic-Studs-62158383451-1.jpg",
            1500.00
        ),
        itemList(
            "Ring",
            "Silver-Toned & Blue Oxidised Classic Studs",
            "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/11476036/2020/3/10/58db6ea8-5842-4c1f-a3a9-379164d4e1c51583834519709-Rubans-Silver-Toned--Blue-Oxidised-Classic-Studs-62158383451-1.jpg",
            1500.00
        )
    )
}