package com.example.stockcourt.Models.Controller

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.stockcourt.R

class ProfileEditActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_edit)
    }

    fun profileEditBackBtnClicked(view: View) {
        val backIntent = Intent(this, ProfileActivity::class.java)
        startActivity(backIntent)
    }

    fun profileEditChangeNameBtnClicked(view: View) {

    }

    fun profileEditChangeEmailBtnClicked(view: View) {

    }

    fun profileEditChangePassBtnClicked(view: View) {

    }

    fun profileEditPackagesBtnClicked(view: View) {

    }

    fun profileEditDeleteAccountBtnClicked(view: View) {

    }

}