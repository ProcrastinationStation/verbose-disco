package com.example.stockcourt.Models.Controller

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.stockcourt.R

class ProfileChangeEmailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_change_email)
    }

    fun editEmailCancelBtnClicked(view: View) {
        val profileEmailBackIntent = Intent(this, ProfileEditActivity:: class.java)
        startActivity(profileEmailBackIntent)
    }
}