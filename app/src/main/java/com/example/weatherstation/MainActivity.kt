package com.example.weatherstation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MainAdapter(MainAdapter.OnClickListener {})
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
}