package com.example.stockcourt.Models.Services

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stockcourt.Models.Controller.Article
import com.example.stockcourt.R

class CommentsAdapter(val bodyResponse: Article.Comments): RecyclerView.Adapter<CustomViewHolder>() {


    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.article_comment, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}
