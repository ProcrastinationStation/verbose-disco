package com.example.stockcourt.Models.Controller

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.NetworkResponse
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.stockcourt.Models.Utilities.GET_REGISTER_TOKEN
import com.example.stockcourt.Models.Utilities.LOGIN_USER
import com.example.stockcourt.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.loginEmail
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.net.CookieHandler
import java.net.CookieManager

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginSignUpText.isClickable

        val cookieManager = CookieManager()
        CookieHandler.setDefault(cookieManager)

        /*val trendsIntent = Intent(this, TrendsActivity::class.java)
        startActivity(trendsIntent)*/

    }

    fun loginLoginBtnClicked(view: View) {

        getCSRF { CSRFToken -> loginUser(CSRFToken, {complete -> } )

        }


    }




    fun loginSignUpClicked(view: View) {
        val registerIntent = Intent(this, RegisterActivity::class.java)
        startActivity(registerIntent)
    }



    fun getCSRF(complete: (String) -> Unit) {
        val queue = Volley.newRequestQueue(this)
        var magicnumber = ""
        val loginRequest = object : StringRequest(Method.GET, GET_REGISTER_TOKEN, Response.Listener { response ->
            complete("$magicnumber")
        }, Response.ErrorListener { error ->
            complete("$magicnumber")
        }) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }
            override fun parseNetworkResponse(response: NetworkResponse?): Response<String> {
                val responseHeaders = response!!.headers
                val rawCookies: String? = responseHeaders["magic-number"].also {
                    magicnumber = it.toString()
                }
                return super.parseNetworkResponse(response);
            }
        }
        queue.add(loginRequest)
    }

    fun loginUser(csrfToken: String, complete: (Boolean) -> Unit) {


        val queue = Volley.newRequestQueue(this)

        val email = loginEmail.text.toString()
        val password = loginPassword.text.toString()


        val jsonBody = JSONObject()
        jsonBody.put("email", email)
        jsonBody.put("password", password)
        val requestBody = jsonBody.toString()


        val loginRequest = object : StringRequest(Method.POST, LOGIN_USER, Response.Listener { response ->
            complete(true)
            Log.d("RESPONSE", "$response")

            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()

            val trendsIntent = Intent(this, TrendsActivity::class.java)
            startActivity(trendsIntent)



        }, Response.ErrorListener { error ->
            Log.d("ERROR", "Could not login user: $error")
            onErrorResponse(error)
            complete(false)
        }) {
            override fun parseNetworkResponse(response: NetworkResponse?): Response<String> {
                val responseHeaders = response!!.headers
                val rawCookies: String? = responseHeaders.get("magic-number").also {
                }
                Log.d("KURAC", rawCookies.toString())
                return super.parseNetworkResponse(response);
            }
            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String>? {
                val map =
                    HashMap<String, String>()
                map["Content-Type"] = "application/json"
                map["CSRF-Token"] = csrfToken
                map["XSRF-TOKEN"] = csrfToken
                map["X-CSRF-Token"] = csrfToken
                return map
            }
        }
        queue.add(loginRequest)
    }

    fun onErrorResponse(error: VolleyError) {
        var body: String = ""
        //get status code here
        val statusCode = error.networkResponse.statusCode.toString()
        //get response body and parse with appropriate encoding
        if (error.networkResponse.data != null) {
            try {
                body =  String(error.networkResponse.data)
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }
        }
        Log.d("BODY", body.toString())
        Toast.makeText(this, "Could not login user, wrong or missing credentials", Toast.LENGTH_SHORT).show()
        //do stuff with the body...
    }


}