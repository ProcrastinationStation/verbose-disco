package com.example.stockcourt.Models.Controller

import UI.ui.MainFragments.BottomNavigationDrawerFragment
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.stockcourt.R

class ProfileActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
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
}