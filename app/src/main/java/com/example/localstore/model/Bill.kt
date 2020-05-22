package com.example.localstore.model

import android.util.Log
import com.example.localstore.adapter.BillAdapter
import com.example.localstore.adapter.BillHttpAdapter
import java.util.*
import kotlin.collections.ArrayList

class Bill(
    var id : Int,
    var date : String,
    var totalCost : Double,
    var products : ArrayList<Product>
)
{



    companion object{
        var allBills = arrayListOf<Bill>()
        var adapter = BillHttpAdapter()
        var productsList : ArrayList<Product> = arrayListOf()
        var counter = mutableMapOf<Int, Int>()

        fun handleProducts(id : Int){
            allBills.forEach {
                if(it.id == id) {
                    it.products.forEach {
                        if (productsList.contains(it)) {
                            var aux: Int = counter[it.id]!!
                            counter[it.id] = aux + 1
                        } else {
                            productsList.add(it)
                            counter.put(it.id, 1)
                        }
                    }
                }
            }
        }

        fun getBill(id : Int) : Bill{
            Log.i("fragmentosxd", "$id")
            var billToReturn : Bill? = null
            allBills.forEach {
                if(it.id == id){
                    billToReturn = it
                }
            }
            return billToReturn!!
        }

    }

}