package com.example.stockcourt.Models.Controller


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.parseAsHtml
import com.example.stockcourt.Models.Utilities.GET_POST
import com.example.stockcourt.R.layout.article
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article.*
import kotlinx.android.synthetic.main.post.*
import kotlinx.android.synthetic.main.post.view.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.io.IOException

class Article: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(article)




        val slug = intent.getStringExtra("slug")

        fetchJsonArticle(slug)





    }



    fun articleBackBtnClicked(view: View) {
        val backIntent = Intent(this, TrendsActivity:: class.java)
        startActivity(backIntent)
    }



    fun fetchJsonArticle(slug: String?) {
        println("Attempting to fetch JSON")


        val request = Request.Builder().url(GET_POST + slug).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: okhttp3.Response) {
                val body = response?.body?.string()


                val gson = GsonBuilder().create()

                val BodyResponseParsed = gson.fromJson(body, Article:: class.java)

                runOnUiThread {
                    textViewArticleHeader.text = BodyResponseParsed.title
                    textViewArticleBody.text = BodyResponseParsed.body.parseAsHtml()

                    println(BodyResponseParsed.created_on)

                    val thumbnailImageView = imageViewArticleHeader
                    Picasso.get().load(BodyResponseParsed.image).into(thumbnailImageView)
                }


            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })

    }



    data class Article(
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
    )





    }