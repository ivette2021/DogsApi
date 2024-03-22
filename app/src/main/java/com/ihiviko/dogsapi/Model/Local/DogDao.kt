package com.ihiviko.dogsapi.Model.Local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ihiviko.dogsapi.Model.Local.Entities.DogDetailEntity
import com.ihiviko.dogsapi.Model.Local.Entities.DogEntity

@Dao
interface DogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBreedList(listBreed: List<DogEntity>)

    @Query("SELECT * FROM dog_list_table ORDER BY breed ASC")
    fun getAllBreedList(): LiveData<List<DogEntity>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllImagesList(dogsImages: List<DogDetailEntity>)

    @Query("SELECT * FROM images_table WHERE breed = :breed")
    fun getAllDoggiesImages(breed : String): LiveData<List<DogDetailEntity>>

}