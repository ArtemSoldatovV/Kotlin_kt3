package org.example

import kotlinx.serialization.Serializable

@Serializable
data class Pizza(
    val name : String,
    val filling : String,
    val cost : Int
)