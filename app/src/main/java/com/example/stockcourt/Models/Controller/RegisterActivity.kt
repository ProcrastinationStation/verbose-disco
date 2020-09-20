package com.example.stockcourt.Models.Controller

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Header
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.stockcourt.Models.Utilities.GET_REGISTER_TOKEN
import com.example.stockcourt.Models.Utilities.REGISTER_USER
import com.example.stockcourt.R.layout.activity_register
import kotlinx.android.synthetic.main.activity_register.*
import okhttp3.Call
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import org.json.JSONObject
import java.io.IOException

class RegisterActivity: AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_register)




        registerBtn.setOnClickListener {
//            val email = editTextTextEmailAddress.text.toString()
//            val displayName = editTextTextPersonName.text.toString()
//            val password = editTextTextPassword2.text.toString()
//
//            Log.d("RegisterActivity", "email is: " +email)
//            Log.d("RegisterActivity", "display name is: " +displayName)
//            Log.d("RegisterActivity", "password is: " +password)

            fetchHeader()

            registerUser(this) {}

            }

        textViewBusinessRegister.isClickable




    }

    fun businessSignUpClicked(view: View) {
        val registerIntent = Intent(this, BusinessRegisterActivity:: class.java)
        startActivity(registerIntent)
    }


    fun fetchHeader(){



        val request = okhttp3.Request.Builder().url(GET_REGISTER_TOKEN).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: okhttp3.Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to get token")
            }

            override fun onResponse(call: Call, response: okhttp3.Response) {
                var csrfToken = response.headers["magic-number"]
                xsrfToken = csrfToken.toString()
            }
        })

    }

    var xsrfToken = ""

    fun print() {
        println(xsrfToken)
    }

   private fun registerUser(context: Context, complete: (Boolean) -> Unit) {

        val email = editTextTextEmailAddress.toString()
        val name = editTextTextPersonName.toString()
        val password = editTextTextPassword2.toString()




        val jsonBody = JSONObject()
        jsonBody.put("email", email)
        jsonBody.put("password", password)
        jsonBody.put("name", name)
        val requestBody = jsonBody.toString()


        val registerRequest = object : StringRequest(Method.POST, REGISTER_USER, Response.Listener { response ->
            complete(true)
            println(response)
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
                headers["XSRF-TOKEN"] = xsrfToken
                headers["Content-Type"] = "application/json"
                return headers
            }
        }


       Volley.newRequestQueue(context).add(registerRequest)



    }}








    /* fun registerUser(context: Context, complete: (Boolean) -> Unit) {

             val jsonBody = JSONObject()
         jsonBody.put("email", editTextTextEmailAddress)
         jsonBody.put("display name", editTextTextPersonName)
         jsonBody.put("password", editTextTextPassword2) */

 /*       val tokenRequest = object : StringRequest(Method.GET, GET_REGISTER_TOKEN, Response.Listener { response ->
            complete(true) },
            Response.ErrorListener { error ->
                Log.d("ERROR", "Could not register user: $error")
                complete(false) }) {

        }

    } */

/*    fun registerUser(context: Context,
                     email: String,
                     password: String,
                     password2: String,
                     name: String,
                     surname: String,
                     accounttype: Boolean,
                     adress: String,
                     city: String,
                     state: String,
                     companyname: String,
                     taxnum: String,
                     birth: String,
                     mobile: String,
                     comment: String,
                     industry: String,
                     complete: (Boolean) -> Unit)

} */