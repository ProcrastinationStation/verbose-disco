package com.example.stockcourt.Models.Controller

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.stockcourt.R.layout.activity_register_business
import kotlinx.android.synthetic.main.activity_register_business.*

class BusinessRegisterActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_register_business)
    }


    fun businessBackBtnClicked(view: View) {
        val backIntent = Intent(this, RegisterActivity:: class.java)
        startActivity(backIntent)
    }
}