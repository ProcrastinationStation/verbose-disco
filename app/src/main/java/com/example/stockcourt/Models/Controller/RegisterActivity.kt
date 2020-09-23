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
import com.example.stockcourt.R.layout.activity_register
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.net.CookieHandler
import java.net.CookieManager
//import kotlin.String as String

class RegisterActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_register)

        val cookieManager = CookieManager()
        CookieHandler.setDefault(cookieManager)



        registerBtn.setOnClickListener {

            getCSRF { CSRFToken -> registerUser(CSRFToken, {complete -> println("Success!")} )

            }


        }




        textViewBusinessRegister.isClickable




    }




    fun businessSignUpClicked(view: View) {
        val registerIntent = Intent(this, BusinessRegisterActivity:: class.java)
        startActivity(registerIntent)
    }





/*    fun fetchHeader() {

        val request = okhttp3.Request.Builder().url(GET_REGISTER_TOKEN).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to get token")
            }

            override fun onResponse(call: Call, response: okhttp3.Response) {
                xsrfToken = response.headers["magic-number"].toString()
                registerUser(this@RegisterActivity) {}
            }
        })
    }*/


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

        val email = registerEmail.text.toString()
        val name = editTextTextPersonName.text.toString()
        val password = editTextTextPassword2.text.toString()


        val jsonBody = JSONObject()
        jsonBody.put("password2", password)
        jsonBody.put("accounttype", false)
        jsonBody.put("address", "")
        jsonBody.put("birth", "1990-01-01")
        jsonBody.put("city", "")
        jsonBody.put("comment", "")
        jsonBody.put("companyname", "")
        jsonBody.put("email", email)
        jsonBody.put("industry", "")
        jsonBody.put("mobile", "")
        jsonBody.put("name", name)
        jsonBody.put("password", password)
        jsonBody.put("state", "")
        jsonBody.put("taxnum", "")
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



    /*fun registerUser(context: Context, complete: (Boolean) -> Unit) {


        var csrfToken = xsrfToken

        val queue = Volley.newRequestQueue(this)


        val email = editTextTextEmailAddress.toString()
        val name = editTextTextPersonName.toString()
        val password = editTextTextPassword2.toString()


        val jsonBody = JSONObject()
        jsonBody.put("email", "lukka.ivanovic@gmail.com")
        jsonBody.put("password", "password")
        jsonBody.put("password2", "password")
        jsonBody.put("name", "test")
        jsonBody.put("surname", "")
        jsonBody.put("accounttype", false)
        jsonBody.put("address", "")
        jsonBody.put("city", "")
        jsonBody.put("state", "")
        jsonBody.put("companyname", "")
        jsonBody.put("taxnum", "")
        jsonBody.put("birth", "1990-01-01")
        jsonBody.put("mobile", "")
        jsonBody.put("comment", "")
        jsonBody.put("industry", "")
        val requestBody = jsonBody.toString()




        val registerRequest = object : StringRequest(Method.POST, REGISTER_USER, Response.Listener { response ->
            complete(true)
            println(response)
        }, Response.ErrorListener { error ->
            Log.d("ERROR", "Could not register user: $error")
            complete(false)
        }) {

            override fun parseNetworkResponse(response: NetworkResponse?): Response<String> {
                val responseHeaders = response!!.headers
                val rawCookies: String? = responseHeaders.get("magic-number").also {
                }
                Log.d("KURAC", rawCookies.toString())
                return super.parseNetworkResponse(response);
            }


*//*                override fun getBodyContentType(): String {
                    return "application/json; charset=utf-8"
                }*//*

                override fun getBody(): ByteArray {
                    return requestBody.toByteArray()
                }

                @Throws(AuthFailureError::class)
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers["Content-Type"] = "application/json"
                    headers["XSRF-TOKEN"] = csrfToken
                    headers["CSRF-Token"] = csrfToken
                    headers["X-CSRF-Token"] = csrfToken
                    return headers
                }

 *//*               override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                        headers.put("Content-Type", "application/json")
                        headers.put("XSRF-TOKEN", csrfToken)
                        return headers

                }*//*
            }

            queue.add(registerRequest)



        }*/
}




    /*fun registerUser(context: Context, complete: (Boolean) -> Unit) {




        val email = editTextTextEmailAddress.toString()
        val name = editTextTextPersonName.toString()
        val password = editTextTextPassword2.toString()


        val jsonBody = JSONObject()
        jsonBody.put("email", email)
        jsonBody.put("password", password)
        jsonBody.put("password2", password)
        jsonBody.put("name", name)
        jsonBody.put("surname", "")
        jsonBody.put("accounttype", false)
        jsonBody.put("address", "")
        jsonBody.put("city", "")
        jsonBody.put("state", "")
        jsonBody.put("companyname", "")
        jsonBody.put("taxnum", "")
        jsonBody.put("birth", "")
        jsonBody.put("mobile", "")
        jsonBody.put("comment", "")
        jsonBody.put("industry", "")
        val requestBody = jsonBody.toString()




            val registerRequest = object : StringRequest(Method.POST, REGISTER_USER, Response.Listener { response ->
                    complete(true)
                    println(response)
                    println("This should come after token")
                }, Response.ErrorListener { error ->
                    Log.d("ERROR", "Could not register user: $error")
                    complete(false)
                }) {


                    override fun getBodyContentType(): String {
                        return "application/json; charset=utf-8"
                    }

                    override fun getBody(): ByteArray {
                        return requestBody.toByteArray()
                    }

                    override fun getHeaders(): MutableMap<String, String> {
                        val headers = HashMap<String, String>()
                        headers["XSRF-TOKEN"] = csrfToken
                        headers["Content-Type"] = "application/json"
                        return headers
                    }
                }

            Volley.newRequestQueue(context).add(registerRequest)*/

