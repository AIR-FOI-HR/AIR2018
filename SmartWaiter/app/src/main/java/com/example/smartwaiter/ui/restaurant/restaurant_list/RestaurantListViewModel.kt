package com.example.smartwaiter.ui.restaurant.restaurant_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartwaiter.repository.AddRestaurantRepository
import hr.foi.air.webservice.model.Restoran
import kotlinx.coroutines.launch
import retrofit2.Response

class RestaurantListViewModel
    (private val repository: AddRestaurantRepository
) : ViewModel() {

    val myResponse: MutableLiveData<Response<List<Restoran>>> = MutableLiveData()

    fun getRestorani(
        table: String,
        method: String,
    ){
        viewModelScope.launch {
            val response = repository.getRestorani(table,method)
            myResponse.value = response
        }
    }
     suspend fun saveActiveRestaurant(activeRestaurant: String) {
        repository.saveActiveRestaurant(activeRestaurant)
    }
}