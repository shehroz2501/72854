package com.example.shahrozkhan_72854.models

class CategoryModel {
    @JvmField
    var name: String? = null
    @JvmField
    var type: String? = null
    @JvmField
    var imageURL: String? = null

    constructor()
    constructor(name: String?, type: String?, imageURL: String?) {
        this.name = name
        this.type = type
        this.imageURL = imageURL
    }
}
