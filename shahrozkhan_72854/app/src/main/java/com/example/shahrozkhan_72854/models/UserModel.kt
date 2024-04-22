package com.example.shahrozkhan_72854.models

class UserModel {
    @JvmField
    var name: String? = null
    @JvmField
    var email: String? = null
    var password: String? = null
    @JvmField
    var profileImg: String? = null
    @JvmField
    var number: String? = null
    @JvmField
    var address: String? = null

    constructor()
    constructor(name: String?, email: String?, password: String?) {
        this.name = name
        this.email = email
        this.password = password
    }
}
