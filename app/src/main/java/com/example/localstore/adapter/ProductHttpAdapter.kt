package com.example.localstore.adapter

import android.util.Log
import com.beust.klaxon.Klaxon
import com.example.localstore.model.Product
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

class ProductHttpAdapter {

    val url = "http://192.168.0.8:1337"

    fun getAll() : List<Product>? {
        var currentUrl = "$url/Product"

        var museumList : ArrayList<Product>? = arrayListOf()
        currentUrl
            .httpGet()
            .responseString { request, response, result ->
                when(result){
                    is Result.Failure -> {
                        val ex =result.getException()
                        Log.i("testingxd", "Error: ${ex.message}")
                    }
                    is Result.Success -> {
                        val data = result.get()
                        Log.i("testingxd",data)
                        try {
                            val museumListAux = Klaxon().parseArray<Product>(data)
                            museumListAux?.forEach {
                                museumList!!.add(it)
                            }
                            if (museumList != null) {
                                Product.allProducts = museumList
                            }
                        }catch (e : Exception){
                            Log.i("testingxd", "Error buscando museos: $e")
                        }
                    }
                }
            }
        return museumList
    }


}