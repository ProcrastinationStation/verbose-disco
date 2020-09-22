package com.example.stockcourt.Models.Services

import UI.ui.MainFragments.HomeFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stockcourt.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.featured_post.view.*

class MainAdapterFeatured(val bodyResponseFeatured: HomeFragment.BodyResponseFeatured): RecyclerView.Adapter<CustomViewHolderFeatured>() {

    override fun getItemCount(): Int {
        return bodyResponseFeatured.featured.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolderFeatured {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRowFeatured = layoutInflater.inflate(R.layout.featured_post, parent, false)
        return CustomViewHolderFeatured(cellForRowFeatured)
    }

    override fun onBindViewHolder(holder: CustomViewHolderFeatured, position: Int) {
        val featured_post = bodyResponseFeatured.featured.get(position)
        holder.view.textViewFeaturedHeader.text = featured_post.title

        val thumbnailImageViewFeatured = holder.view.imageViewFeatured
        Picasso.get().load(featured_post.image).into(thumbnailImageViewFeatured)
    }
}


class CustomViewHolderFeatured(val view: View): RecyclerView.ViewHolder(view) {

}