package com.example.stockcourt.Models.Controller

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stockcourt.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home)


        RecyclerViewFeatured.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        RecyclerViewMain.layoutManager = LinearLayoutManager(this)

        
    }



}