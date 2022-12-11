package com.example.tp_kotlin_modesto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tp_kotlin_modesto.databinding.ActivityMainBinding
import com.example.tp_kotlin_modesto.response.QuoteResponse


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: RetrofitViewModel
    private lateinit var retrofitService: RetrofitService
    private lateinit var adapter: ImageListAdapter

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

        binding.buttonRandomPhoto.setOnClickListener {
            viewModel.refresh()

        }
        binding.imageView.setOnClickListener {
            val intent = Intent(applicationContext, ImageDetails::class.java)
            startActivity(intent)
        }
        adapter = ImageListAdapter(
            onClick = {
                val intent = Intent(applicationContext, ImageDetails::class.java)
                intent.putExtra("image", it.urls.raw)
                intent.putExtra("likes", it.likes)
                intent.putExtra("liked", it.liked_by_user)
                intent.putExtra("author", it.user.username)
                intent.putExtra("description", it.description)
                startActivity(intent)
            }
        )
        binding.recyclerView.adapter = adapter
        initObserver()

    }
    private fun initObserver() {
        viewModel.data.observe(this)
        {
            adapter.data=it
        }
    }
}

