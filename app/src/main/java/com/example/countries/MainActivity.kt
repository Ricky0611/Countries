package com.example.countries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.countries.databinding.ActivityMainBinding
import com.example.countries.db.ApiHelper
import com.example.countries.db.MainRepository
import com.example.countries.network.ApiClient
import com.example.countries.network.ApiService
import com.example.countries.ui.adapter.CountryAdapter
import com.example.countries.ui.viewmodel.MainViewModel
import com.example.countries.utils.CountryViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var countryAdapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViewModel()
        setupAdapter()
        setupObserver()

        viewModel.getCountries()
    }

    private fun setupObserver() {
        viewModel.countryList.observe(this) {
            countryAdapter.setData(it)
        }
    }

    private fun setupAdapter() {
        countryAdapter = CountryAdapter()

        val dividerItemDecoration = DividerItemDecoration(this, LinearLayout.VERTICAL)
        binding.recyclerView.apply {
            adapter = countryAdapter
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun setupViewModel() {
        val retrofit = ApiClient.getApiClient()
        val apiService = retrofit.create(ApiService::class.java)
        val apiHelper = ApiHelper(apiService)
        val mainRepository = MainRepository(apiHelper)

        viewModel = ViewModelProvider(this, CountryViewModelFactory(mainRepository))[MainViewModel::class.java]
        binding.viewModel = viewModel
    }
}