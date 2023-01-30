package com.example.project.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tasks")
@Parcelize
data class Task(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val nateTitle: String,

    val nateBody: String

): Parcelable