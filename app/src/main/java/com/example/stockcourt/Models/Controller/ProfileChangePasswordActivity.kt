package com.example.stockcourt.Models.Controller

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.stockcourt.Models.Utilities.CHANGE_PASS
import com.example.stockcourt.R
import kotlinx.android.synthetic.main.activity_profile_change_password.*
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.net.CookieHandler
import java.net.CookieManager
import java.net.CookiePolicy

class ProfileChangePasswordActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_change_password)

        val cookieManager = CookieManager()
        CookieHandler.setDefault(cookieManager)

        CookieHandler.setDefault(CookieManager(null, CookiePolicy.ACCEPT_ALL))

    }

    fun profilePasswordCancelBtnClicked(view: View) {
        val profilePasswordBackIntent = Intent(this, ProfileEditActivity::class.java)
        startActivity(profilePasswordBackIntent)
    }

    fun profilePasswordDoneBtnClicked(view: View) {

        changePass{}

    }

    var token = LoginActivity.token
    var cookie = LoginActivity.sessionCookie1

    private fun changePass(complete: (Boolean) -> Unit) {

        val queue = Volley.newRequestQueue(this)

        val oldPass = profileChangePassCurrentPass.text.toString()
        val newPass = profileChangePassNewPass.text.toString()

        val jsonBody = JSONObject()
        jsonBody.put("currentpassword", oldPass)
        jsonBody.put("password", newPass)
        val requestBody = jsonBody.toString()

        val changePassRequest = object: StringRequest(
            Method.POST,
            CHANGE_PASS,
            Response.Listener {
                response ->  complete(true)
                Log.d("RESPONSE", "$response")

                Toast.makeText(this, "Password succesfully changed!", Toast.LENGTH_SHORT).show()

                val passChangedIntent = Intent(this, ProfileActivity::class.java)
                startActivity(passChangedIntent)
            },
            Response.ErrorListener { error ->
                Log.d("ERROR", "Couldn`t change password: $error")
                complete(false)
            }

        ) {

            override fun getBody(): ByteArray {
            return requestBody.toByteArray()
        }


            override fun getHeaders(): Map<String, String>? {
                val map = HashMap<String, String>()
                map["Content-Type"] = "application/json"
                map["set-cookie"] = cookie
                map["magic-number"] = token
                map["cookie"] = cookie
                return map
            }

        }



                queue.add(changePassRequest)
    }

}