package com.example.weatherstation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView


class MainActivity() : AppCompatActivity() {

    val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val reWeather = findViewById<RecyclerView>(R.id.reWeather)
        reWeather.adapter = adapter

        viewModel.weatherData.observe(this, Observer {
            it?.let {
//                adapter.submitList(it.records)
            }
        })

        viewModel.items.observe(this, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
    }

    private val adapter = object : MainAdapter(MainAdapter.OnClickListener {}) {
        override fun deleteItem(itemId: String) {
            viewModel.deleteById(itemId)
        }
    }
}