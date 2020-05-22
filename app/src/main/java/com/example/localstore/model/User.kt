package com.example.localstore.model

import com.example.localstore.adapter.UserHttpAdapter

class User (
    var id : Int,
    var userName : String
){

    companion object{

        var currentUser : User? = null
        var adapter = UserHttpAdapter()

        fun login(user: String, password : String) : User?{
            return adapter.execute(user, password).get()
        }

    }

}