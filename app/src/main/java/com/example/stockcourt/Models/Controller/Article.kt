package com.example.stockcourt.Models.Controller

import UI.ui.MainFragments.HomeFragment
import android.app.PendingIntent.getActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.stockcourt.Models.Controller.Article.article
import com.example.stockcourt.Models.Services.MainAdapter
import com.example.stockcourt.Models.Utilities.GET_POST
import com.example.stockcourt.Models.Utilities.GET_POSTS
import com.example.stockcourt.R
import com.example.stockcourt.R.layout.article
import com.example.stockcourt.R.layout.post
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.post.view.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.util.logging.Logger.global

class Article: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(article)








        val slug = intent.getStringExtra("slug")



        println(slug)

        fetchJsonArticle(slug)


    }

    fun setContentView(article: article) {
        textViewArticleHeader.text = article.title
        textViewArticleBody.text = article.body
    }



    fun fetchJsonArticle(slug: String?) {
        println("Attempting to fetch JSON")


        val request = Request.Builder().url(GET_POST + slug).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: okhttp3.Response) {
                val body = response?.body?.string()
                println(body)


                val gson = GsonBuilder().create()

                val BodyResponseParsed = gson.fromJson(body, Article.article:: class.java)
                println(BodyResponseParsed)

                textViewArticleHeader.text = BodyResponseParsed.title

            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })

    }


    data class article(
        val id: Long,
        val title: String,
        val description: String,
        val meta_title: String,
        val meta_description: String,
        val image: String,
        val slug: String,
        val body: String,
        val body_contents: String,
        val approved: Long,
        val featured: Long,
        val created_on: String,
        val updated_on: String,
        val views: Long,
        val written_by_user: Long,
        val approved_by_user: Long,
       // val tags: [
       // String,
       // String
       // ],
        val name: String



    ){}





    }