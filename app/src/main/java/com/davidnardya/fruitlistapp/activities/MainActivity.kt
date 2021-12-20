package com.davidnardya.fruitlistapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.davidnardya.fruitlistapp.R
import com.davidnardya.fruitlistapp.adapter.MainAdapter
import com.davidnardya.fruitlistapp.databinding.ActivityMainBinding
import com.davidnardya.fruitlistapp.factory.MainViewModelFactory
import com.davidnardya.fruitlistapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    //Properties
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        defineBinding()
        initViewModel()
        getDataFromApi()
    }

    //Private methods
    private fun getDataFromApi() {
        viewModel.getFruits()
        viewModel.fruitList.observe(this, Observer { response ->
            if (response != null) {
                adapter.setData(response)
                Log.d("fruitList", "Loaded successfully")
            } else {
                Log.e("fruitList", "Something went wrong")
            }
        })
    }

    private fun initViewModel() {
        val factory = MainViewModelFactory(this.application)
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    private fun defineBinding() {
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
    }
}