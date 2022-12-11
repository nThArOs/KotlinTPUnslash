package com.example.tp_kotlin_modesto

import MonoViewModel
import MonoViewModelFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.tp_kotlin_modesto.databinding.ActivityImageDetailsBinding

class ImageDetails : AppCompatActivity() {

    private lateinit var binding: ActivityImageDetailsBinding
    private lateinit var viewModel: MonoViewModel
    private lateinit var retrofitService: RetrofitService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityImageDetailsBinding.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)

        retrofitService = RetrofitApi.getService()
        viewModel = ViewModelProvider(
            this,
            MonoViewModelFactory(retrofitService)
        )[MonoViewModel::class.java]

        viewModel.data.observe(this) {
                Glide.with(this)
                    .load(it.urls.raw)
                    .into(binding.imageViewDetail)
            binding.likes.text = it.likes.toString()
            binding.liked.text = it.liked_by_user.toString()
            binding.description.text = it.description
            binding.author.text = it.user.username
        }
    }
}