package com.example.stockcourt.Models.Controller

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.NetworkResponse
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.stockcourt.Models.Utilities.GET_REGISTER_TOKEN
import com.example.stockcourt.Models.Utilities.REGISTER_USER
import com.example.stockcourt.R.layout.activity_register_business
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register_business.*
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.net.CookieHandler
import java.net.CookieManager

class BusinessRegisterActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_register_business)

        val cookieManager = CookieManager()
        CookieHandler.setDefault(cookieManager)


        businessRegisterBtn.setOnClickListener {

            getCSRF { CSRFToken -> registerUser(CSRFToken, {complete -> println("Success!")} )

            }


        }
    }


    fun businessBackBtnClicked(view: View) {
        val backIntent = Intent(this, RegisterActivity:: class.java)
        startActivity(backIntent)
    }


    fun getCSRF(complete: (String) -> Unit) {
        val queue = Volley.newRequestQueue(this)
        var magicnumber = ""
        val registerRequest = object : StringRequest(Method.GET, GET_REGISTER_TOKEN, Response.Listener { response ->
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
        queue.add(registerRequest)
    }

    fun registerUser(csrfToken: String, complete: (Boolean) -> Unit) {


        val queue = Volley.newRequestQueue(this)

        val companyname = businessCompanyNameTxt.text.toString()
        val email = businessEmailTxt.text.toString()
        val password = businessPassTxt.text.toString()
        val address = businessAddressTxt.text.toString()
        val city = businessCityTxt.text.toString()
        val ZIPcode = businessZIPTxt.text.toString()
        val country = businessCountryTxt.text.toString()
        val taxNum = businessTaxTxt.text.toString()


        val jsonBody = JSONObject()
        jsonBody.put("password2", password)
        jsonBody.put("accounttype", true)
        jsonBody.put("address", address)
        jsonBody.put("birth", "1990-01-01")
        jsonBody.put("city", city)
        jsonBody.put("comment", "")
        jsonBody.put("companyname", companyname)
        jsonBody.put("email", email)
        jsonBody.put("industry", "")
        jsonBody.put("mobile", "")
        jsonBody.put("name", "")
        jsonBody.put("password", password)
        jsonBody.put("state", country)
        jsonBody.put("taxnum", taxNum)
        jsonBody.put("surname", "")
        val requestBody = jsonBody.toString()

        println(email)

        val registerRequest = object : StringRequest(Method.POST, REGISTER_USER, Response.Listener { response ->
            complete(true)
            Log.d("RESPONSE", "$response")
        }, Response.ErrorListener { error ->
            Log.d("ERROR", "Could not register user: $error")
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
        queue.add(registerRequest)
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
        //do stuff with the body...
    }


}