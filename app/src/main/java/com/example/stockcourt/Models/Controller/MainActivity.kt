package com.example.stockcourt.Models.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.stockcourt.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    mainSignUpTxt.isClickable

    }




    fun loginEmailBtnClicked(view: View) {
        val loginEmailIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginEmailIntent)
    }

    fun loginGoogleBtnClicked(view: View) {
 /*       val loginGoogleIntent = Intent(this, TrendsActivity::class.java)
        startActivity(loginGoogleIntent)*/
    }

    fun mainSignUpTxtClicked(view: View) {
        val mainSignUpIntent = Intent(this, RegisterActivity:: class.java)
        startActivity(mainSignUpIntent)
    }


}