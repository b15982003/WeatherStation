package com.example.weatherstation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout


class MainActivity() : AppCompatActivity() {

    val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val reWeather = findViewById<RecyclerView>(R.id.reWeather)
        reWeather.adapter = adapter

        val swipe = findViewById<SwipeRefreshLayout>(R.id.swiperefresh)
        swipe.setOnRefreshListener {
            swipe.isRefreshing = true
            viewModel.deleteAll()
            viewModel.getWeatherStatus()
        }


        viewModel.weatherData.observe(this, Observer {
            it?.let {
//                adapter.submitList(it.records)
            }
        })

        viewModel.items.observe(this, Observer {
            it?.let {
                adapter.submitList(it)
                swipe.isRefreshing = false
            }
        })
    }

    private val adapter = object : MainAdapter(MainAdapter.OnClickListener {}) {
        override fun deleteItem(itemId: String) {
            viewModel.deleteById(itemId)
        }
    }
}