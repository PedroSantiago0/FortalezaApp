package com.fortaleza.app.data.model

data class HealthUnit(
    val number: Int,
    val name: String,
    val address: String,
    val hours: String,
    val isOpen: Boolean,
    val phone: String,
    val lat: Double,
    val lng: Double
)
