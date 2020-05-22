package com.example.localstore.adapter

import android.os.AsyncTask
import android.util.Log
import com.beust.klaxon.Klaxon
import com.example.localstore.model.Bill
import com.example.localstore.model.Client
import com.example.localstore.model.Product
import com.example.localstore.model.ShopCart
import com.github.kittinunf.fuel.core.Parameters
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import java.util.*
import kotlin.collections.ArrayList

class BillHttpAdapter : AsyncTask<String, Integer, Unit>() {

    override fun doInBackground(vararg p0: String?) {
        Log.i("testingxd","llegamos")
        var shopCart = ShopCart.shoppingCart
        var counter = ShopCart.cartCount

        var currentUrl = "$url/Bill"

        var body = listOf(
            "date" to Date().toString(),
            "totalCost" to ShopCart.calculatePrice().toString(),
            "client_bill_FK" to Client.currentClient!!.id
        )

        val (request, response, result) = currentUrl
            .httpPost(body)
            .responseString()
        Log.i("testingxd","vamos bien")
        if(result is Result.Success){
            Log.i("testingxd","1")
            var data = result.get()
            var insertedBill = Klaxon().parse<Bill>(data)
            currentUrl = "$url/BillProduct"
            shopCart.forEach {
                var productBody = listOf(
                    "quantity" to counter[it.id],
                    "product_FK" to it.id,
                    "bill_FK" to insertedBill!!.id
                )
                val (request, response, result) =currentUrl
                    .httpPost(productBody)
                    .responseString()
            }
        }
        ShopCart.shoppingCart = arrayListOf()
        ShopCart.cartCount = mutableMapOf<Int, Int>()
        Bill.adapter.getAll()
    }

    val url = "http://192.168.0.8:1337"

    fun getAll() : List<Bill>? {
        var currentUrl = "$url/allBills"

        var museumList : ArrayList<Bill>? = arrayListOf()
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
                            val museumListAux = Klaxon().parseArray<Bill>(data)
                            museumListAux?.forEach {
                                museumList!!.add(it)
                            }
                            if (museumList != null) {
                                Bill.allBills = museumList
                            }
                        }catch (e : Exception){
                            Log.i("testingxd", "Error buscando museos: $e")
                        }
                    }
                }
            }
        return museumList
    }

    fun newBill(body : Parameters){
        var currentUrl = "$url/Bills"
        currentUrl
            .httpPost(body)
            .responseString{ request, response, result ->
                if(result is Result.Failure) {
                    var ex = result.getException()
                    Log.i("httpError", "Error: $ex")
                }
            }
    }

    fun addProduct(body: Parameters){
        var currentUrl = "$url/BillProduct"
        currentUrl
            .httpPost(body)
            .responseString{ request, response, result ->
                if(result is Result.Failure) {
                    var ex = result.getException()
                    Log.i("httpError", "Error: $ex")
                }
            }
    }

}