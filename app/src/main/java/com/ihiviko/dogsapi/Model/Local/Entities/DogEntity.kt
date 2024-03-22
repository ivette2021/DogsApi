package com.ihiviko.dogsapi.Model.Local.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="dog_list_table")
data class DogEntity(@PrimaryKey val breed:String)