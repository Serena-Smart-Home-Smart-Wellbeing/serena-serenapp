package com.example.serena.UI.Shop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.serena.API.ISerenProductResData
import com.example.serena.R
import com.example.serena.SerenUtils
import com.example.serena.UI.MainActivity

class ProductAdapter(private val productList: ArrayList<ISerenProductResData>, private val mainActivity: MainActivity): RecyclerView.Adapter<ProductAdapter.ProductHolder>() {
    private lateinit var context: Context
    private lateinit var itemView: View
    private val serenUtils: SerenUtils = SerenUtils()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        itemView = LayoutInflater.from(parent.context).inflate(R.layout.serena_product_card, parent, false)
        context = parent.context
        return ProductHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val currentItem = productList[position]
        val image = currentItem.image_name
        Glide.with(context).load(image).into(holder.image)

        itemView.setOnClickListener{
            mainActivity.serenPlaceProductDetail(currentItem.id)
        }

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ProductHolder(viewsItem: View): RecyclerView.ViewHolder(viewsItem) {
        val image: ImageView = viewsItem.findViewById(R.id.image)
    }

}

