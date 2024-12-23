package com.fanatics.codechallenge.data.model

import androidx.compose.runtime.Stable

@Stable
data class Person(
    val id: Long,
    val name: String,
    val gender: String,
    val status: String,
    val species: String,
)
