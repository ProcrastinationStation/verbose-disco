package com.example.stockcourt.Models.Controller


import UI.ui.MainFragments.ArticleMenuDrawerFragment
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.parseAsHtml
import azadev.kotlin.css.Stylesheet
import azadev.kotlin.css.color
import com.example.stockcourt.Models.Utilities.GET_ARTICLE_COMMENTS
import com.example.stockcourt.Models.Utilities.GET_POST
import com.example.stockcourt.R
import com.example.stockcourt.R.layout.article
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article.*
import kotlinx.android.synthetic.main.post.*
import kotlinx.css.CSSBuilder
import kotlinx.css.Contain
import kotlinx.css.h1
import okhttp3.*
import org.jsoup.Jsoup
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class Article: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(article)



        val slug = intent.getStringExtra("slug")

        fetchJsonArticle(slug)


    }



    fun articleBackBtnClicked(view: View) {
        val backIntent = Intent(this, TrendsActivity::class.java)
        startActivity(backIntent)
    }


    fun articleMoreBtnClicked(view: View) {
        val moreMenuDrawerFragment = ArticleMenuDrawerFragment()
        moreMenuDrawerFragment.show(supportFragmentManager, moreMenuDrawerFragment.tag)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.article_more_menu, menu)
        return true
    }

    var postId: Long = 0

    private fun fetchJsonArticle(slug: String?) {


        val request = Request.Builder().url(GET_POST + slug).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call, response: okhttp3.Response) {
                val body = response?.body?.string()


                val gson = GsonBuilder().create()

                val BodyResponseParsed = gson.fromJson(body, Article::class.java)

                runOnUiThread {
                    textViewArticleHeader.text = BodyResponseParsed.title

                    textViewArticleBody.text = BodyResponseParsed.body.parseAsHtml()

                    val html = Jsoup.parse(BodyResponseParsed.body).toString()

                    postId = BodyResponseParsed.id

                    println(postId)

                    println(html)

/*                    val css = Stylesheet{

                        body {
                            color = 0xffffff
                        }
                        h1 {
                        color = 0x66bd9f
                    }


                    }

                    css.renderToFile(html)*/


                    articleAuthor.text = BodyResponseParsed.written_by_user.toString()


                    val date = BodyResponseParsed.created_on

                    //2020-07-19T16:06:35.000Z

                    var delimiter1 = "-"
                    var delimiter2 = "T"

                    var dateParsed = date.split(delimiter1, delimiter2)

                    var month = ""


                    when(dateParsed[1]) {
                        "01" -> month = "JAN"
                        "02" -> month = "FEB"
                        "03" -> month = "MAR"
                        "04" -> month = "APR"
                        "05" -> month = "MAY"
                        "06" -> month = "JUN"
                        "07" -> month = "JUL"
                        "08" -> month = "AUG"
                        "09" -> month = "SEP"
                        "10" -> month = "OCT"
                        "11" -> month = "NOV"
                        "12" -> month = "DEC"
                    }

                    val dateProperFormat = dateParsed[2] + " " + month + " " + dateParsed[0]

                    articleDate.text = dateProperFormat


                    val thumbnailImageView = imageViewArticleHeader
                    Picasso.get().load(BodyResponseParsed.image).into(thumbnailImageView)
                }

                fetchArticleComment()

            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })

    }


    fun fetchArticleComment() {

        val request = Request
            .Builder()
            .url("$GET_ARTICLE_COMMENTS$postId&limit=5")
            .header("Content-Type", "application/json")
            .build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()

                val gson = GsonBuilder().create()

                val bodyResponseArticleCommentsParsed = gson.fromJson(body, CommentsParsed::class.java)

                println(body)



            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to get article comments")
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

    data class CommentsParsed(
        val comments: List<Comments>
    )

    data class Comments(
        val id: Long,
        val blog_post_id: Long,
        val date: String,
        val display_name: String,
        val comment: String
    )





    }