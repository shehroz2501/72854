package com.example.shahrozkhan_72854.models

class OrderModel {
    @JvmField
    var productName: String? = null
    @JvmField
    var productPrice: String? = null
    @JvmField
    var currentDate: String? = null
    @JvmField
    var currentTime: String? = null
    @JvmField
    var totalQuantity: String? = null
    @JvmField
    var totalPrice = 0
    @JvmField
    var documentId: String? = null

    constructor()
    constructor(
        productName: String?,
        productPrice: String?,
        currentDate: String?,
        currentTime: String?,
        totalQuantity: String?,
        totalPrice: Int,
        documentId: String?
    ) {
        this.productName = productName
        this.productPrice = productPrice
        this.currentDate = currentDate
        this.currentTime = currentTime
        this.totalQuantity = totalQuantity
        this.totalPrice = totalPrice
        this.documentId = documentId
    }
}
