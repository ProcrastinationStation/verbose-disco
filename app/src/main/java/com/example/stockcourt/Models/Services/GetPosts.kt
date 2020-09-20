package com.example.stockcourt.Models.Services

import UI.ui.MainFragments.HomeFragment
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.Json
import com.beust.klaxon.Parser
import com.example.stockcourt.Models.Utilities.GET_POSTS
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException
import java.lang.StringBuilder
import java.net.URL

object GetPosts {

    fun fetchJsonPost() {
        println("Attempting to fetch JSON")

        val request = Request.Builder().url(GET_POSTS).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: okhttp3.Response) {
                val body = response?.body?.string()
                println(body)


                val gson = GsonBuilder().create()

                val BodyResponseParsed = gson.fromJson(body, BodyResponse:: class.java)
                println(BodyResponseParsed)


        /*        runOnUiThread {
                    recyclerView_main.adapter = MainAdapter(BodyResponseParsed)
                }

         */
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })

    }

data class BodyResponse(
     val news: List<Post>
) {
}


data class Post (
val id: Long,
val title: String,
val description: String,
@Json(name = "meta_title")
val metaTitle: String,
@Json(name = "meta_description")
val metaDescription: String,
val image: String,
val slug: String,
val body: String,
@Json(name = "body_contents")
val bodyContents: String,
val approved: Long,
val featured: Long,
@Json(name = "created_on")
val createdOn: String,
@Json(name = "updated_on")
val updatedOn: String,
val views: Long,
@Json(name = "written_by_user")
val writtenByUser: Long,
@Json(name = "approved_by_user")
val approvedByUser: Any? = null,
val tags: List<String>,
val name: String
) {}



}}