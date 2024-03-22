package com.ihiviko.dogsapi.Model

import android.util.Log
import androidx.lifecycle.LiveData
import com.ihiviko.dogsapi.Model.Local.DogDao
import com.ihiviko.dogsapi.Model.Local.Entities.DogDetailEntity
import com.ihiviko.dogsapi.Model.Remote.RetrofitClient

class DogRepository(private val dogDao: DogDao) {
    private val networkService = RetrofitClient.retrofitInstance()
    val breedListLivedata = dogDao.getAllBreedList()

    suspend fun fetchBreed() {
        val service = kotlin.runCatching { networkService.getBreedsList() }
        service.onSuccess {
            when (it.code()) {
                in 200..299 ->it.body()?.let {
                    dogDao.insertAllBreedList(fromInternetToBreedEntity(it))
                }
                else -> Log.d("REPO", "${it.code()} - ${it.errorBody()}")
            }
        }
        service.onFailure {
            Log.e("REPO", "${it.message}")
        }
    }
    //Recibe la raza y realiza la solicitud guardando el elemento en la Base de datos.
    suspend fun fetchDogImages(breed: String) {
        val service = kotlin.runCatching { networkService.getImagesList(breed) }
        service.onSuccess {
            when (it.code()) {
                200 -> it.body()?.let {
                    dogDao.insertAllImagesList(fromInternetToImagesEntity(it, breed))
                }
                else -> Log.d("REPO-IMG", "${it.code()} - ${it.errorBody()}")
            }
        }
        service.onFailure {
            Log.e("REPO", "${it.message}")
        }
    }

    // Retorna las imagenes por raza desde la base de datos.
    fun getAllImagesByBreed(breed: String): LiveData<List<DogDetailEntity>> {
        return dogDao.getAllDoggiesImages(breed)
    }

}