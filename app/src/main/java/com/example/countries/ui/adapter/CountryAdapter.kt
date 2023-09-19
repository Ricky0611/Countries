package com.example.countries.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.data.Country
import com.example.countries.databinding.RecyclerviewCountryBinding

class CountryAdapter: RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    private lateinit var binding: RecyclerviewCountryBinding
    private lateinit var list: List<Country>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        binding = RecyclerviewCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = list[position]
        holder.bind(country)
    }

    override fun getItemCount(): Int {
        return if (this::list.isInitialized) list.size else 0
    }

    fun setData(countryList: List<Country>) {
        list = countryList
        notifyDataSetChanged()
    }

    class CountryViewHolder(private val binding: RecyclerviewCountryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) {
            binding.country = country
        }
    }
}