package com.example.localstore.model

import com.example.localstore.adapter.ProductHttpAdapter

class Product (
    var id : Int,
    var name : String,
    var type : String,
    var description : String,
    var price : Double
) {

    var image : String

    init {
        image = "http://192.168.0.8:1337/picture?id=$id"
    }

    companion object{
        var allProducts = arrayListOf<Product>()
        val adapter = ProductHttpAdapter()

    }

}