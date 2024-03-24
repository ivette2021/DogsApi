package com.ihiviko.dogsapi

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ihiviko.dogsapi.Model.Local.DataBase.DogDataBase
import com.ihiviko.dogsapi.Model.Local.DogDao
import com.ihiviko.dogsapi.Model.Local.Entities.DogEntity
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestDao {
    private lateinit var dDao: DogDao
    private lateinit var dDB: DogDataBase

    @Before
    fun setup(){
        val context= ApplicationProvider.getApplicationContext<android.content.Context>()
        dDB=  Room.inMemoryDatabaseBuilder(context,DogDataBase::class.java).build()
        dDao=dDB.dogDao()
    }
    @Test
    fun testInsertRazas() = runBlocking  {
        val RazasList= listOf(
            DogEntity("Perro1" ),
            DogEntity("Perro2" ),
            DogEntity("Perro3" ),
            DogEntity("Perro4" )
        )
        dDao.insertAllBreedList(RazasList)

        val razasLiveData = dDao.getAllBreedList()
        val listRazas = razasLiveData.value?: emptyList()

        MatcherAssert.assertThat(listRazas, CoreMatchers.not(emptyList()))
        MatcherAssert.assertThat(listRazas.size, CoreMatchers.equalTo(4))

    }
    @After
    fun shutdown(){
        dDB.close()
    }

}