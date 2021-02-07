package com.example.smartwaiter.repository

import hr.foi.air.webservice.RetrofitInstance
import hr.foi.air.webservice.model.Restoran
import retrofit2.Response

class AddRestaurantRepository {

    suspend fun getRestorani(
        table: String,
        method: String,
    ): Response<List<Restoran>> {
        return RetrofitInstance.api.getRestorani(table, method)
    }

    suspend fun insertRestoran(
        table: String,
        method: String,
        restaurantName: String,
        restaurantAddress: String,
        GPS_Longitude: Double,
        GPS_Latitude: Double
    ): Response<String> {
        return RetrofitInstance.api.insertRestoran(table, method, restaurantName, restaurantAddress, GPS_Longitude, GPS_Latitude)
    }
}