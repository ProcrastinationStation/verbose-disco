package com.example.stockcourt.Models.Controller

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.stockcourt.Models.Utilities.GET_REGISTER_TOKEN
import com.example.stockcourt.R.layout.activity_register
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject

class RegisterActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_register)


        registerBtn.setOnClickListener {
            val email = editTextTextEmailAddress.text.toString()
            val displayName = editTextTextPersonName.text.toString()
            val password = editTextTextPassword2.text.toString()

            Log.d("RegisterActivity", "email is: " +email)
            Log.d("RegisterActivity", "display name is: " +displayName)
            Log.d("RegisterActivity", "password is: " +password)

            

        }


    }



    fun registerUser(context: Context, complete: (Boolean) -> Unit) {

        val jsonBody = JSONObject()
        jsonBody.put("email", editTextTextEmailAddress)
        jsonBody.put("display name", editTextTextPersonName)
        jsonBody.put("password", editTextTextPassword2)

        val tokenRequest = object : StringRequest(Method.GET, GET_REGISTER_TOKEN, Response.Listener { response ->
            complete(true) },
            Response.ErrorListener { error ->
                Log.d("ERROR", "Could not register user: $error")
                complete(false) }) {

        }

    }


}