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
        holder.view.textViewHeader.text = post.title

        holder.view.textViewArticle.text = post.description

        holder.view.textViewDatePublished.text = post.createdOn

        val thumbnailImageView = holder.view.imageView_postHeader
        Picasso.get().load(post.image).into(thumbnailImageView)

        holder.slug = post.slug

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