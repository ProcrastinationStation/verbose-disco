package com.example.stockcourt.Models.Controller

import UI.ui.MainFragments.BottomNavigationDrawerFragment
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.stockcourt.Models.Utilities.GET_USER
import com.example.stockcourt.R
import okhttp3.*
import java.io.IOException
import java.net.CookieHandler
import java.net.CookieManager

class ProfileActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        val cookieManager = CookieManager()
        CookieHandler.setDefault(cookieManager)

        getUser()
    }


    //The top back button from the profile activity
    fun profileBackBtnClicked(view: View) {
        val backIntent = Intent(this, TrendsActivity::class.java)
        startActivity(backIntent)
    }

    //The settings button that opens the menu in the profile activity
    fun profileSettingsBtnClicked(view: View) {
        val bottomNavDrawerFragment = BottomNavigationDrawerFragment()
        bottomNavDrawerFragment.show(supportFragmentManager, bottomNavDrawerFragment.tag)
    }

    //the function that draws the menu in the profile activity
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.profile_settings_menu, menu)
        return true
    }


        var client = OkHttpClient()

        fun getUser() {
            val request = Request.Builder()
                .url(GET_USER)
                .header("Content-Type", "application/json")
                .build()




            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {}
                override fun onResponse(call: Call, response: Response) =
                    println(response.body?.string())
            })

        }

    }