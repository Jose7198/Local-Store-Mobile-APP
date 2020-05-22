package com.example.localstore.model

import com.example.localstore.adapter.ClientHttpAdapter

class Client (
    var id : Int,
    var name : String,
    var lastName : String,
    var gender : String,
    var phone : String,
    var bills : ArrayList<Bill>
)
{

    companion object{
        var currentClient : Client? = null
        var adapter = ClientHttpAdapter()
    }

}