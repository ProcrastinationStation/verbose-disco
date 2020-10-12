package com.example.stockcourt.Models.Controller

import UI.ui.MainFragments.BottomNavigationDrawerFragment
import UI.ui.MainFragments.ProfileImageEditDrawerFragment
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.stockcourt.Models.Utilities.GET_PROFILE_IMAGE
import com.example.stockcourt.Models.Utilities.GET_USER
import com.example.stockcourt.R
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import java.io.IOException
import java.net.CookieHandler
import java.net.CookieManager
import java.net.CookiePolicy
import java.net.CookieStore

class ProfileActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)



        getUser({complete -> getUserImage()})








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

    //The edit image button that opens the edit image menu in the profile activity
    fun profileImageEditBtnClicked(view: View) {
        val profileImageMenuFragment = ProfileImageEditDrawerFragment()
        profileImageMenuFragment.show(supportFragmentManager, profileImageMenuFragment.tag)
    }


    //the function that draws the menu in the profile activity
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.profile_settings_menu, menu)
        return true
    }

    var token = LoginActivity.token
    var sessionCookie = LoginActivity.sessionCookie1





/*
       private val client = OkHttpClient()


       fun getUser() {
            val request = okhttp3.Request.Builder()
                .url(GET_USER)
                .header("Content-Type", "application/json")
                .header("set-cookie", sessionCookie1)
                .header("magic-number", token)
                .build()


           client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {}
                override fun onResponse(call: Call, response: okhttp3.Response) =
                    println(response.body?.string())
            })

        }
*/

    var id = ""

    fun getUser( complete: (Boolean) -> Unit) {

      val queue = Volley.newRequestQueue(this)

       val stringRequest = object: StringRequest(
           Request.Method.GET, GET_USER, { response ->

               val body = response.toString()

               println(body)

               val gson = GsonBuilder().create()

               val bodyParsed = gson.fromJson(body, User::class.java)

               profileTextViewUserName.text = bodyParsed.user.name

               profileTextViewUserEmail.text = bodyParsed.user.email

               id = bodyParsed.user.id.toString()

            complete(true)

             if (bodyParsed.user.type) {
                   profileTextViewUserAccountType.text = "Business"
               } else {
                   profileTextViewUserAccountType.text = "Personal"
               }


           },
           { error ->
               Log.d("Error", "Could not get user: $error")
            complete(false)
           })

       {
           override fun getHeaders(): Map<String, String>? {
               val map =
                   HashMap<String, String>()
               map["Content-Type"] = "application/json"
               map["set-cookie"] = sessionCookie
               map["magic-number"] = token
               map["cookie"] = sessionCookie
               return map
           }
       }


       queue.add(stringRequest)


   }



    fun getUserImage() {

        val queue = Volley.newRequestQueue(this)

        val stringRequest = object: StringRequest(
            Method.GET, GET_PROFILE_IMAGE + id, {
                response ->

                val body = response.toString()

                val gson = GsonBuilder().create()

                val image_url = gson.fromJson(body, imageURL::class.java)
                

                if (image_url.image_url == null) {

                    println("No image link from server")
                }
                else {

                    val profileImageView = profileCircleImageView
                    Picasso.get().load(image_url.image_url).into(profileImageView)

                }




            }, { error ->
                Log.d("Error", "Couldn`t get user image: $error")
            }
        )

        {}

         queue.add(stringRequest)
    }



    data class User(
        val user: UserParsed
    )

    data class UserParsed(
        val id: Long,
        val email: String,
        val date_registered: String,
        val date_activated: String,
        val type: Boolean,
        val name: String,
        val date_of_birth: String,
        val mobile_number: String,
        val company_name: String,
        val tax_number: String,
        val state: String,
        val city: String,
        val address: String,
        val comment: String,
        val business_industry_type: String
    )

    data class imageURL(
        val image_url: String
    )

}