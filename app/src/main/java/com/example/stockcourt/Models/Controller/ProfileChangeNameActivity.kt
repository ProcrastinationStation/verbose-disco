package com.example.stockcourt.Models.Controller

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.stockcourt.Models.Controller.LoginActivity.Companion.token
import com.example.stockcourt.Models.Utilities.CHANGE_NAME
import com.example.stockcourt.R
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_profile_change_name.*
import okhttp3.*
import org.json.JSONObject
import java.net.CookieHandler
import java.net.CookieManager
import java.net.CookiePolicy

class ProfileChangeNameActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_change_name)


        val cookieManager = CookieManager()
        CookieHandler.setDefault(cookieManager)

        CookieHandler.setDefault(CookieManager(null, CookiePolicy.ACCEPT_ALL))

    }

    fun editNameCancelBtnClicked(view: View) {
        val profileNameBackIntent = Intent(this, ProfileEditActivity::class.java)
        startActivity(profileNameBackIntent)
    }

    fun editNameDoneBtnClicked(view: View) {

        changeName()

    }

    val token = LoginActivity.token
    val sessionCookie = LoginActivity.sessionCookie1

    fun changeName() {

        val name = profileChangeNameEditTextName.text.toString()


        val queue = Volley.newRequestQueue(this)


        val jsonBody = JSONObject()
        jsonBody.put("name", name)
        jsonBody.put("type", false)
        jsonBody.put("address", "")
        jsonBody.put("city", "")
        jsonBody.put("state", "")
        jsonBody.put("company_name", "")
        jsonBody.put("tax_number", "")
        jsonBody.put("date_of_birth", "")
        jsonBody.put("mobile_number", "")
        jsonBody.put("comment", "")
        jsonBody.put("business_industry_type", "")
        val requestBody = jsonBody.toString()


        val stringRequest = object: StringRequest(
            Method.POST, CHANGE_NAME, {response ->

                val body = response.toString()

                println(body)

                val gson = GsonBuilder().create()

            },

            { error ->
                Log.d("Error", "Could not change name: $error")
            }
        )

        {

            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }

            override fun getHeaders(): Map<String, String>? {
                val map = HashMap<String, String>()
                map["Content-Type"] = "application/json"
                map["set-cookie"] = sessionCookie
                map["magic-number"] = token
                map["cookie"] = sessionCookie
                return map
            }
        }

                queue.add(stringRequest)

    }

}