package com.example.smartwaiter.ui.restaurant.restaurant_list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.smartwaiter.R
import com.example.smartwaiter.repository.AddRestaurantRepository
import hr.foi.air.webservice.model.Restoran
import kotlinx.android.synthetic.main.fragment_restaurant_list.*
import com.example.database.UserPreferences
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RestaurantList : Fragment(R.layout.fragment_restaurant_list) {
    lateinit var lv: ListView
    lateinit var restaurants: Array<Restoran>
    lateinit var adapter: ArrayAdapter<Restoran>
    private lateinit var userPreferences: UserPreferences

    private lateinit var viewModel: RestaurantListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        userPreferences = UserPreferences(requireContext())

        super.onViewCreated(view, savedInstanceState)

        val repository = AddRestaurantRepository(userPreferences)
        val viewModelFactory = RestaurantListModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RestaurantListViewModel::class.java)

        lv = view.findViewById<ListView>(R.id.restaurant_list_view)

        btnAddRestaurant.setOnClickListener {
            val action = RestaurantListDirections.actionRestaurantListFragmentToAddRestaurant(1)
            findNavController().navigate(action)
        }

        adapter = ArrayAdapter<Restoran>(requireActivity(), R.layout.centered_list_layout)
        viewModel.getRestorani(table="Lokal", method = "select")
        lv.adapter=adapter

        viewModel.myResponse.observe(viewLifecycleOwner, {
            val response = it.body()
            if (response != null) {
                for(restaurant in response){
                    adapter.add(restaurant)
                }
            }
        })
        lv.setOnItemClickListener { parent, view, position, id ->
            val element = adapter.getItem(position)
            val action = RestaurantListDirections.actionRestaurantListFragmentToMeniFragment()
            lifecycleScope.launch {
                viewModel.saveActiveRestaurant(activeRestaurant = element!!.id_lokal.toString())
            }
            findNavController().navigate(action)

        }
    }

}