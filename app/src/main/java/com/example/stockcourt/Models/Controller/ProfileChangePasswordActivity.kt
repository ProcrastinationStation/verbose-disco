package com.example.stockcourt.Models.Controller

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.stockcourt.R

class ProfileChangePasswordActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_change_password)
    }

    fun profilePasswordCancelBtnClicked(view: View) {
        val profilePasswordBackIntent = Intent(this, ProfileEditActivity::class.java)
        startActivity(profilePasswordBackIntent)
    }

    fun profilePasswordDoneBtnClicked(view: View) {

    }
}