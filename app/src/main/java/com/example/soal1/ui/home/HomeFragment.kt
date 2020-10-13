package com.example.soal1.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.soal1.BaseActivity
import com.example.soal1.R
import com.example.soal1.global.Preferences
import com.example.soal1.viewmodel.home.HomeViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.lang.reflect.Type

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    var imageUrlList = arrayListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(
            this,
            HomeViewModelFactory((activity as BaseActivity).preferences.getIp(), listener)
        ).get(
            HomeViewModel::class.java
        )
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //homeViewModel.getImage()
        homeViewModel.getImageList()
    }

    val listener = object : Preferences.URLBuilder.GenericResponseListener {
        override fun onSuccessResponse(str: String) {
            if(str.startsWith("")){

            }
            var jsonArray = JSONArray()
            var newUrl = ""
            val type: Type = object : TypeToken<List<String>>() {}.type




            GlobalScope.launch(Dispatchers.Main) {
                if(str.contains(",")){
                    imageUrlList.clear()
                    imageUrlList.addAll(Gson().fromJson(str, type))
                    imageUrlList.forEachIndexed {index, it ->
                        if(it.startsWith("/")){
                            if(!str.endsWith(".png")) return@launch
                            imageUrlList[index] = it.substring(1, it.length)
                        }
                    }
                    var adapter =SliderAdapter(imageUrlList)
                    image_slider.setSliderAdapter(adapter)
                    image_slider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                    image_slider.startAutoCycle()
                }
                else{
                    Log.e("shohiebsense ",str)
                    if(!str.endsWith(".png")) return@launch
                    newUrl = str.replace("\\", "")
                    Glide.with(this@HomeFragment).load(Preferences.URLBuilder.generateWithoutSlash(newUrl.substring(2, newUrl.length-2))).into(image_banner)

                }

            }
        }

    }
}