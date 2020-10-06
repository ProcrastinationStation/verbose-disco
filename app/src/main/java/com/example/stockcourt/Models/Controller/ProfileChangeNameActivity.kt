package com.example.stockcourt.Models.Controller

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.stockcourt.R
import kotlinx.android.synthetic.main.activity_profile_change_name.*

class ProfileChangeNameActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_change_name)


    }

    fun editNameCancelBtnClicked(view: View) {
        val profileNameBackIntent = Intent(this, ProfileEditActivity::class.java)
        startActivity(profileNameBackIntent)
    }

    fun editNameDoneBtnClicked(view: View) {

    }
}