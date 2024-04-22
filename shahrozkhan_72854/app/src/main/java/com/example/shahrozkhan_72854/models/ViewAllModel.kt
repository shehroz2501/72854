package com.example.shahrozkhan_72854.models

import java.io.Serializable

class ViewAllModel : Serializable {
    @JvmField
    var name: String? = null
    @JvmField
    var description: String? = null
    var type: String? = null
    @JvmField
    var image_url: String? = null
    @JvmField
    var rating: String? = null
    @JvmField
    var price: String? = null

    constructor()
    constructor(
        name: String?,
        description: String?,
        type: String?,
        image_url: String?,
        rating: String?,
        price: String?
    ) {
        this.name = name
        this.description = description
        this.type = type
        this.image_url = image_url
        this.rating = rating
        this.price = price
    }
}
