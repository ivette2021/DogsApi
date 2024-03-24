package com.ihiviko.dogsapi

import com.ihiviko.dogsapi.Model.Local.DogDao
import com.ihiviko.dogsapi.Model.Local.Entities.DogEntity

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
@RunWith(JUnit4::class)
class TestDogs {
    private lateinit var dogs: DogEntity
    lateinit var dogDao: DogDao

    @Before
    fun setup() {
        dogs = DogEntity(breed = "Test")
    }

    // verifica que el constructor de DogEntity funcione correctamente
    @Test
    fun testConstructor() {
        assert(dogs.breed == "Test")
    }

    //verificando si la longitud de la cadena breed de dogs es igual a 4
    @Test
    fun testIdIsNull() {
        assert(dogs.breed.length== 4)
    }
}

