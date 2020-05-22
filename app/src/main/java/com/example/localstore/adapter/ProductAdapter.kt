package com.example.localstore.adapter

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.localstore.R
import com.example.localstore.model.Product
import com.example.localstore.model.ShopCart
import com.example.localstore.views.ProductsFragment

class ProductAdapter
    (
        private val productsList : ArrayList<Product>,
        private val context : ProductsFragment,
        private val recyclerView : RecyclerView
) : RecyclerView.Adapter<ProductAdapter.MyViewHolder>()
{
    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        val product = productsList[p1]
        p0.productName.text = product.name
        p0.productType.text = product.type
        p0.productPrice.text = "$ ${product.price}"
        Glide.with(context).load(product.image).into(p0.productImage)
        p0.addProduct.setOnClickListener {
            ShopCart.insertIntoShopCart(product)
            Toast.makeText(context.context, "${product.name} agregado al carrito", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val itemView = LayoutInflater.from(p0.context).inflate(
            R.layout.product_layout,
            p0,
            false
        )
        return MyViewHolder(itemView)
    }

    inner class MyViewHolder (view : View) : RecyclerView.ViewHolder(view) {

        var productName : TextView
        var productType : TextView
        var productPrice : TextView
        var productImage : ImageView
        var addProduct : ImageView

        init{

            productName = view.findViewById(R.id.tv_name)
            productType = view.findViewById(R.id.tv_type)
            productPrice = view.findViewById(R.id.tv_price)
            productImage = view.findViewById(R.id.iv_product_image)
            addProduct = view.findViewById(R.id.iv_add)


        }



    }
}