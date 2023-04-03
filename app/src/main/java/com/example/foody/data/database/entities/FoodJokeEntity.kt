package com.example.foody.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foody.models.FoodJoke
import com.example.foody.utils.Constants.FOOD_JOKES_TABLE


@Entity(tableName = FOOD_JOKES_TABLE)
class FoodJokeEntity(
    @Embedded
    var foodJoke: FoodJoke
) {
    @PrimaryKey(autoGenerate = false)
    var id = 0
}