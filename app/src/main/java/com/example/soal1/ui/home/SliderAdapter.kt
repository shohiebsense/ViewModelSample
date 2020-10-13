package com.example.soal1.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.soal1.R
import com.example.soal1.global.Preferences
import com.smarteist.autoimageslider.SliderViewAdapter
import kotlinx.android.synthetic.main.item_image.view.*

class SliderAdapter(val imageUrlList: ArrayList<String>) : SliderViewAdapter<SliderAdapter.SliderViewHolder>() {


    inner class SliderViewHolder(itemView: View) : SliderViewAdapter.ViewHolder(itemView){

    }

    override fun getCount(): Int {
        return imageUrlList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): SliderViewHolder{
        return SliderViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_image, null))
    }

    override fun onBindViewHolder(viewHolder: SliderViewHolder, position: Int) {
        var url = imageUrlList[position]
        Glide.with(viewHolder.itemView).load(Preferences.URLBuilder.generate(url).substring(0, Preferences.URLBuilder.generate(url).length-1)).fitCenter().into(viewHolder.itemView.image_banner)
    }
}