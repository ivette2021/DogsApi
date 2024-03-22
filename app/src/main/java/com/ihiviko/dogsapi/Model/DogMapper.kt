package com.ihiviko.dogsapi.Model

import com.ihiviko.dogsapi.Model.Local.Entities.DogDetailEntity
import com.ihiviko.dogsapi.Model.Local.Entities.DogEntity
import com.ihiviko.dogsapi.Model.Remote.FromInternet.DogDetail
import com.ihiviko.dogsapi.Model.Remote.FromInternet.Dogs

fun fromInternetToBreedEntity(dogs: Dogs): List<DogEntity> {
    val breedNames = dogs.message.keys
    return breedNames.map{breedNames->

        DogEntity(breed = breedNames) }
}
fun fromInternetToImagesEntity(iImages: DogDetail, breed: String): List<DogDetailEntity> {
    val imageName= iImages.message
    return imageName.map {imageName ->
        DogDetailEntity(imageUrl = imageName, breed = breed) }
}
