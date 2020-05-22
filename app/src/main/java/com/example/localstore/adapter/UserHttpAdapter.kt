package com.example.localstore.adapter

import android.os.AsyncTask
import android.util.Log
import com.beust.klaxon.Klaxon
import com.example.localstore.model.Client
import com.example.localstore.model.User
import com.github.kittinunf.fuel.core.Parameters
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result

class UserHttpAdapter : AsyncTask<String, Integer, User?>() {

    val url = "http://192.168.0.8:1337"

    override fun doInBackground(vararg params: String?): User? {

        var currentUrl = "$url/login"

        var user = params[0]
        var pass = params[1]
        val body = listOf(
            "userName" to user,
            "password" to pass
        )
        var usuario : User? = null
        val (request, response, result) = currentUrl
            .httpPost(body)
            .responseString()

        when(result){
            is Result.Failure ->{
                val ex = result.getException()
                Log.i("testingxd", "Error $ex")
            }
            is Result.Success -> {
                val data = result.get()
                usuario = Klaxon().parse<User>(data)
                if(usuario != null){
                    User.currentUser = usuario
                    Client.currentClient = Client.adapter.getClient(usuario.id)
                }
            }
        }
        return usuario
    }

    fun register(body : Parameters){
        var currentUrl = "$url/User"
        currentUrl
            .httpPost(body)
            .responseString{ request, response, result ->
                when(result){
                    is Result.Failure -> {
                        var ex = result.getException()
                        Log.i("httpError", "Error: $ex")
                    }
                    is Result.Success -> {
                        val data = result.get()
                        var newUser = Klaxon().parse<User>(data)
                        User.currentUser = newUser
                        val newBody = listOf(
                            "user_person_FK" to newUser!!.id
                        )
                        Client.adapter.updateClient(newBody, newUser.id)
                    }
                }

            }
    }



}