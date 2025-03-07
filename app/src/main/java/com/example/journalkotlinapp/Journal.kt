package com.example.journalkotlinapp

import com.google.firebase.Timestamp


data class Journal(
    val title: String,
    val thoughts: String,
    val imageUrl: Number,

    val userId: String,
    val timeAdded: Timestamp,
    val username: String
)
