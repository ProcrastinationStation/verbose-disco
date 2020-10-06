package com.example.stockcourt.Models.Controller

import UI.ui.MainFragments.*
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.stockcourt.R
import kotlinx.android.synthetic.main.activity_trends.*
import java.net.CookieHandler
import java.net.CookieManager


class TrendsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trends)

        val cookieManager = CookieManager()
        CookieHandler.setDefault(cookieManager)



        val homeFragment = HomeFragment()
        val commoditiesFragment = CommoditiesFragment()
        val currenciesFragment = CurrenciesFragment()
        val indicesFragment = IndicesFragment()
        val stocksFragment = StocksFragment()

        makeCurrentFragment(homeFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.stocksFragment -> makeCurrentFragment(stocksFragment)
                R.id.indicesFragment -> makeCurrentFragment(indicesFragment)
                R.id.trendsFragment -> makeCurrentFragment(homeFragment)
                R.id.commoditiesFragment -> makeCurrentFragment(commoditiesFragment)
                R.id.currenciesFragment -> makeCurrentFragment(currenciesFragment)
            }
            true
        }


        
    }


    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
    }

    fun homeProfileBtnClicked(view: View) {
        val profileIntent = Intent(this, ProfileActivity::class.java)
        startActivity(profileIntent)
    }
}