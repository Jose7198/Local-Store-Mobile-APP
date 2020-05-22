package com.example.localstore.model

import com.example.localstore.adapter.BillAdapter
import com.example.localstore.adapter.BillHttpAdapter
import com.example.localstore.adapter.ProductHttpAdapter
import java.lang.Math.round
import java.time.LocalDateTime
import java.util.*

class ShopCart {

    companion object{
        var shoppingCart = arrayListOf<Product>()
        var cartCount = mutableMapOf<Int, Int>()
        var adapter = BillHttpAdapter()


        fun existsOnCart(id : Int) : Int {
            var count : Int = 0
            shoppingCart.forEach {
                if(it.id == id){
                    count ++
                }
            }
            return count
        }

        fun insertIntoShopCart(product : Product){
            var count = existsOnCart(product.id)
            if(count == 0){
                shoppingCart.add(product)
                cartCount.put(product.id, 1)
            }else{
                var num: Int = cartCount[product.id]!!
                cartCount[product.id] = num + 1
            }
        }

        fun calculatePrice() : Double{
            var totalPrice = 0.0
            shoppingCart.forEach {
                totalPrice += (it.price * cartCount[it.id]!!)
            }
            return (round(totalPrice * 100) / 100).toDouble()
        }

        fun deleteFromCart(product : Product) : Int{
            var toReturn = 0
            if(cartCount[product.id] == 1){
                shoppingCart.remove(product)
                cartCount.remove(product.id)
            }else{
                var num: Int = cartCount[product.id]!!
                cartCount[product.id] = num - 1
                toReturn =1
            }
            return toReturn
        }

        fun buy(){
            adapter.execute().get()
        }

    }

}