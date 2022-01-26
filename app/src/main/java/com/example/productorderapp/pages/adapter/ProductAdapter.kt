package com.example.productorderapp.pages.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.productorderapp.databinding.ProductRowBinding
import com.example.productorderapp.pages.ProductDetailPage
import com.example.productorderapp.pages.models.BilgilerData
import com.example.productorderapp.pages.objects.UserInfo

class ProductAdapter(private val context: Context, private val arrList: List<BilgilerData>): BaseAdapter() {
    override fun getCount(): Int {
       return arrList.size
    }

    override fun getItem(p0: Int): Any {
        return p0
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        val binding = ProductRowBinding.inflate(LayoutInflater.from(context), p2, false)

        val product = arrList.get(p0)

        val url = product.images!!.get(0)!!.normal
        binding.productTitle.text = product.productName
        binding.productPrice.text = product.price
        Glide.with(context).load(url).into(binding.productImage)

        binding.layoutId.setOnClickListener {

            val i = Intent(context,ProductDetailPage::class.java)
            i.putExtra("productName",product.productName)
            i.putExtra("productPrice",product.price)
            i.putExtra("productImage",url)
            i.putExtra("description",product.description)

            UserInfo.productId = product.productId.toString()
            context.startActivity(i)

        }

        return binding.root
    }
}