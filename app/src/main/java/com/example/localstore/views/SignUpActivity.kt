package com.example.localstore.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.localstore.R
import com.example.localstore.model.Client
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btn_signup_action.setOnClickListener {
            signup()
        }

    }

    fun signup(){
        var name = input_name.text.toString()
        var lastName = input_last_name.text.toString()
        var gender = spinner.selectedItem.toString()
        var phone = input_phone.text.toString()
        var userName = input_user.text.toString()
        var password = input_pass.text.toString()
        var clientParameters = listOf(
            "name" to name,
            "lastName" to lastName,
            "gender" to gender,
            "phone" to phone
        )
        Client.adapter.register(clientParameters, userName, password)
        goToHomeActivity()
    }

    fun goToHomeActivity(){
        var intent = Intent(
            this,
            HomeActivity :: class.java
        )
        startActivity(intent)
    }

}
