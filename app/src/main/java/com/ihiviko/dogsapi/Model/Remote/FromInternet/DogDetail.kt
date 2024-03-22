package com.ihiviko.dogsapi.Model.Remote.FromInternet

import androidx.room.Entity

data class DogDetail(
    val message: List<String>,
    val status: String
)