package com.fanatics.codechallenge.data.model

import androidx.compose.runtime.Stable

@Stable
data class Person(
    val id: Long,
    val name: String,
    val height: Double,
    val mass: Double,
    val homeworld: String,
)
