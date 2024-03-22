package com.ihiviko.dogsapi.Model.Local.Entities

import androidx.room.Entity

@Entity(
    tableName = "images_table",
    primaryKeys = ["breed", "imageUrl"]
)
data class DogDetailEntity (
    val imageUrl: String,
    val breed: String,
    var fav: Boolean = false
)