package com.example.stockcourt.Models.Controller

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.stockcourt.Models.Utilities.GET_REGISTER_TOKEN
import com.example.stockcourt.Models.Utilities.REGISTER_USER
import com.example.stockcourt.R.layout.activity_register
import kotlinx.android.synthetic.main.activity_register.*
import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException
import kotlin.String as String

class RegisterActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_register)




        registerBtn.setOnClickListener {

            fetchHeader()
        }




        textViewBusinessRegister.isClickable




    }




    fun businessSignUpClicked(view: View) {
        val registerIntent = Intent(this, BusinessRegisterActivity:: class.java)
        startActivity(registerIntent)
    }



    var csrfToken = ""


    fun fetchHeader() {

        val request = okhttp3.Request.Builder().url(GET_REGISTER_TOKEN).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to get token")
            }

            override fun onResponse(call: Call, response: okhttp3.Response) {
                csrfToken = response.headers["magic-number"].toString()
                registerUser(this@RegisterActivity) {}
            }
        })
    }


    /*fun registerUser2(context: Context, complete: (Boolean) -> Unit) {

        val email = editTextTextEmailAddress.toString()
        val name = editTextTextPersonName.toString()
        val password = editTextTextPassword2.toString()

        val jsonBody = JSONObject()
        jsonBody.put("email", email)
        jsonBody.put("password", password)
        jsonBody.put("password2", "")
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


        val okHttpClient = OkHttpClient
        val requestBody2 = requestBody.toRequestBody()
        val request = Request.Builder()
            .method("POST", requestBody2)
            .url(REGISTER_USER)
            .header("Content-Type", "application/json")
            .header("XSRF-TOKEN", csrfToken)
            .build()

        okHttpClient.newCall(Request).enqueue(object : Callback) {
            override fun onFailure(call: Call, e: IOException) {
                complete(false)
                Log.d("ERROR", "Could not register user $response")
            }

            override fun onResponse(call: Call, response: Response) {
                complete(true)
            }
        }



    }*/



        fun registerUser(context: Context, complete: (Boolean) -> Unit) {


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


                override fun getBodyContentType(): String {
                    return "application/json; charset=utf-8"
                }

                override fun getBody(): ByteArray {
                    return requestBody.toByteArray()
                }

                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers["Content-Type"] = "application/json"
                    headers["XSRF-TOKEN"] = csrfToken
                    return headers
                }

 /*               override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                        headers.put("Content-Type", "application/json")
                        headers.put("XSRF-TOKEN", csrfToken)
                        return headers

                }*/
            }

            Volley.newRequestQueue(context).add(registerRequest)



        }
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

