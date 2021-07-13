package com.example.shestore.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.shestore.Model.CategoryModel
import com.example.shestore.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cardview_category.view.*

class CategoryAdapter(val categoryModel : List<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cardview_category, parent, false))
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        holder.itemView.card_category_name.text = categoryModel[position].categoryName
        Picasso.get().load(categoryModel[position].categoryImageURL.toUri()).into(holder.itemView.card_category_imageView)
    }

    override fun getItemCount(): Int = categoryModel.size
}