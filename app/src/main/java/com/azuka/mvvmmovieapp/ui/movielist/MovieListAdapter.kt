package com.azuka.mvvmmovieapp.ui.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.azuka.mvvmmovieapp.R
import com.azuka.mvvmmovieapp.data.model.Genre

class MovieListAdapter(private var titleList: MutableList<Genre>) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val listTitle: TextView = itemView.findViewById(R.id.cv_movie_genre_list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.genre_list, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.listTitle.text = titleList[position].toString()
    }

    override fun getItemCount(): Int {
        return titleList.size
    }

}