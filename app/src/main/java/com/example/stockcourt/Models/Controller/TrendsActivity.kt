package com.example.stockcourt.Models.Controller

import UI.ui.MainFragments.*
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.stockcourt.R
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_trends.*
import kotlinx.android.synthetic.main.fragment_home.*
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
            R.id.stocksFragmentBtn -> {
                makeCurrentFragment(stocksFragment)
                true
            }
            R.id.indicesFragmentBtn -> {
                makeCurrentFragment(indicesFragment)
                true
            }
            R.id.trendsFragmentBtn -> {
                makeCurrentFragment(homeFragment)
                true
            }
            R.id.commoditiesFragmentBtn -> {
                makeCurrentFragment(commoditiesFragment)
                true
            }
            R.id.currenciesFragmentBtn -> {
                makeCurrentFragment(currenciesFragment)
                true
            }
            else -> false
        } }

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