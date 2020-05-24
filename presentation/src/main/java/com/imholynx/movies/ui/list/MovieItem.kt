package com.imholynx.movies.ui.list

import com.bumptech.glide.Glide
import com.imholynx.movies.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.movie_item.*


class MovieItem(val movieId: Int, private val title: String, private val imageUrl: String) :
    Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        Glide.with(viewHolder.itemView)
            .load(imageUrl)
            .into(viewHolder.imageView)
        viewHolder.titleTextView.text = title
    }

    override fun getLayout() = R.layout.movie_item

}