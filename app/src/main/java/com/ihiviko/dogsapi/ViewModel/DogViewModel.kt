package com.ihiviko.dogsapi.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ihiviko.dogsapi.Model.DogRepository
import com.ihiviko.dogsapi.Model.Local.DataBase.DogDataBase
import com.ihiviko.dogsapi.Model.Local.Entities.DogDetailEntity
import com.ihiviko.dogsapi.Model.Local.Entities.DogEntity
import kotlinx.coroutines.launch

class DogViewModel(application: Application): AndroidViewModel(application)  {

    private val repository: DogRepository

    init {
        val db = DogDataBase.getDatabase(application)
        val dogsDao = db.dogDao()
        repository = DogRepository(dogsDao)

        viewModelScope.launch {
            repository.fetchBreed()
        }
    }

    fun getBreedList(): LiveData<List<DogEntity>> = repository.breedListLivedata

    private var breedSelected : String = ""

    fun getImagesByBreedFromInternet(breed: String) = viewModelScope.launch {
        breedSelected = breed
        repository.fetchDogImages(breed)
    }

    fun getImages(): LiveData<List<DogDetailEntity>> = repository.getAllImagesByBreed(breedSelected)

    fun updateFav(dogDetailEntity: DogDetailEntity) = viewModelScope.launch {
        Log.d("repoFav", " repo fav")
        repository.updateFavImages(dogDetailEntity)
    }
    fun deleteallFav() {
        viewModelScope.launch {
            Log.d("repoFav", " repo fav")
            repository.deleteFavImages()
        }
    }
}