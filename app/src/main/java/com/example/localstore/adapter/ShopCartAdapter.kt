package com.example.localstore.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.localstore.R
import com.example.localstore.model.Product
import com.example.localstore.model.ShopCart
import com.example.localstore.views.ProductsFragment
import com.example.localstore.views.ShopCartFragment
import kotlinx.android.synthetic.main.shop_cart_layout.view.*

class ShopCartAdapter
    (
    private val productsList : ArrayList<Product>,
    private val context : ShopCartFragment,
    private val recyclerView : RecyclerView
) : RecyclerView.Adapter<ShopCartAdapter.MyViewHolder>()
{
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val itemView = LayoutInflater.from(p0.context).inflate(
            R.layout.shop_cart_layout,
            p0,
            false
        )
        return MyViewHolder(itemView, p1)
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        val product = productsList[p1]
        p0.productName.text = product.name
        var productQuantity = ShopCart.cartCount[product.id]!!
        p0.productQuantity.text = "$productQuantity"
        p0.productPrice.text = "$ ${product.price}"
        var totalCost = productQuantity * product.price
        p0.productTotalCost.text = "$ $totalCost"
        Glide.with(context).load(product.image).into(p0.productImage)
        p0.deleteProduct.setOnClickListener {
            var handler = ShopCart.deleteFromCart(product)
            if(handler == 0){
                context.iniciarRecyclerVirew(ShopCart.shoppingCart, context, recyclerView)
            }else{
                var productQuantity = ShopCart.cartCount[product.id]!!
                p0.productQuantity.text = "$productQuantity"
                var totalCost = productQuantity * product.price
                p0.productTotalCost.text = "$ $totalCost"
            }
            context.updatePrice()
        }
    }

    inner class MyViewHolder (view : View, p1 : Int) : RecyclerView.ViewHolder(view) {

        var productName : TextView
        var productPrice : TextView
        var productQuantity : TextView
        var productTotalCost : TextView
        var productImage : ImageView
        var deleteProduct : ImageView

        init {

            productName = view.findViewById(R.id.tv_product_name)
            productPrice = view.findViewById(R.id.tv_product_price)
            productQuantity = view.findViewById(R.id.tv_product_qtty)
            productTotalCost = view.findViewById(R.id.tv_totalCost)
            productImage = view.findViewById(R.id.iv_product)
            deleteProduct = view.findViewById(R.id.iv_remove)
        }
    }
}