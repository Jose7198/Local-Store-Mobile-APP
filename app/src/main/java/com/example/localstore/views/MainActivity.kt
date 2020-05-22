package com.example.localstore.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.localstore.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_login.setOnClickListener {
            goToLogInActivity()
        }

        btn_signup.setOnClickListener {
            goToSignUpActivity()
        }


    }

    fun goToLogInActivity(){
        var intent = Intent(
            this,
            LoginActivity :: class.java
        )
        startActivity(intent)
    }

    fun goToSignUpActivity(){
        var intent = Intent(
            this,
            SignUpActivity :: class.java
        )
        startActivity(intent)
    }
}
