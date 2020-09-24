package com.example.stockcourt.Models.Services

import UI.ui.MainFragments.HomeFragment
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stockcourt.Models.Controller.Article
import com.example.stockcourt.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article.*
import kotlinx.android.synthetic.main.post.view.*

class MainAdapter(val bodyResponse: HomeFragment.BodyResponse): RecyclerView.Adapter<CustomViewHolder>() {

    //Number of items
    override fun getItemCount(): Int {
        return bodyResponse.news.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.post, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val post = bodyResponse.news.get(position)

        holder.view.textViewHeader.text = post.title //set post title

        holder.view.textViewArticle.text = post.description //set post description

        val thumbnailImageView = holder.view.imageView_postHeader //set post image
        Picasso.get().load(post.image).into(thumbnailImageView)

        holder.slug = post.slug //set slug to pass to Article activity

        val date = post.created_on




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

        holder.view.textViewDatePublished.text = dateProperFormat



    }


}


class CustomViewHolder(val view: View, var slug: String = ""): RecyclerView.ViewHolder(view) {


    init {
        view.setOnClickListener {

            val intent = Intent(view.context, Article::class.java)


            intent.putExtra("slug", slug)

            view.context.startActivity(intent)

        }
    }
}