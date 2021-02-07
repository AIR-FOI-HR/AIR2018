package com.example.database.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.webservice.model.Meal
import com.example.webservice.model.Order


@Entity(tableName = "orderedmeals")
data class OrderedMeal(
    @PrimaryKey(autoGenerate = false)
    val meal: Meal,
    val order: Order
)