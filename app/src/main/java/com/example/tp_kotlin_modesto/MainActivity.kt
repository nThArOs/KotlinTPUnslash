package com.example.tp_kotlin_modesto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.tp_kotlin_modesto.databinding.ActivityMainBinding
//import com.google.android.ads.mediationtestsuite.viewmodels.ViewModelFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: RetrofitViewModel
    private lateinit var retrofitService: RetrofitService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)

        retrofitService = RetrofitApi.getService()
        viewModel = ViewModelProvider(
            this,
            RetrofitViewModelFactory(retrofitService)
        )[RetrofitViewModel::class.java]

        initObserver()

        binding.buttonRandomPhoto.setOnClickListener {
            viewModel.refresh()
        }

       // binding.imageView.setOnClickListener{
          //  val intent = Intent(applicationContext, Details)
           // startActivity(intent)
      //gi  }
    }



    //binding.buttonRandomPhoto

    private fun initObserver() {
        viewModel.data.observe(this)
        {
            Glide.with(this)
                .load(it.urls.raw)
                .into(binding.imageView)
        }
    }

}