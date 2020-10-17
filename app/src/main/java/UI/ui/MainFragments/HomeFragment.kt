package UI.ui.MainFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.beust.klaxon.Json
import com.example.stockcourt.Models.Controller.Article
import com.example.stockcourt.Models.Controller.ProfileActivity
import com.example.stockcourt.Models.Controller.TrendsActivity
import com.example.stockcourt.Models.Services.MainAdapter
import com.example.stockcourt.Models.Services.MainAdapterFeatured
import com.example.stockcourt.Models.Utilities.GET_FEATURED
import com.example.stockcourt.Models.Utilities.GET_POSTS
import com.example.stockcourt.R
import com.example.stockcourt.R.layout.fragment_home
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_trends.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException


class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(fragment_home, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        RecyclerViewFeatured.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        RecyclerViewMain.layoutManager = LinearLayoutManager(context)


        fetchJsonFeatured()

        fetchJsonPost()
    }

    //Get featured post model
    fun fetchJsonFeatured() {
        println("Attempting to fetch JSON featured posts")

        val request = Request.Builder().url(GET_FEATURED).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: okhttp3.Response) {
                val body = response?.body?.string()

                val gson = GsonBuilder().create()

                val BodyResponseFeaturedParsed = gson.fromJson(body, BodyResponseFeatured:: class.java)

            getActivity()?.runOnUiThread(){
                RecyclerViewFeatured.adapter = MainAdapterFeatured(BodyResponseFeaturedParsed)
            }


            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request(featured)")
            }
        })


    }


    //Get main post model
    fun fetchJsonPost() {
        println("Attempting to fetch JSON")

        val request = Request.Builder().url(GET_POSTS).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: okhttp3.Response) {
                val body = response?.body?.string()


                val gson = GsonBuilder().create()

                val BodyResponseParsed = gson.fromJson(body, BodyResponse:: class.java)

                getActivity()?.runOnUiThread(){
                    RecyclerViewMain.adapter = MainAdapter(BodyResponseParsed)
                }
                
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })

    }

    data class BodyResponse(
        val news: List<Post>
    )


    data class Post (
        val id: Long,
        val title: String,
        val description: String,
        val metaTitle: String,
        val metaDescription: String,
        val image: String,
        val slug: String,
        val body: String,
        val bodyContents: String,
        val approved: Long,
        val featured: Long,
        val created_on: String,
        val updatedOn: String,
        val views: Long,
        val writtenByUser: Long,
        val approvedByUser: Any? = null,
        val tags: List<String>,
        val name: String
    )

    data class BodyResponseFeatured(
        val featured: List<Featured>
    )

    data class Featured (
        val id: Long,
        val slug: String,
        val image: String,
        val title: String
    )


}