package com.fortaleza.app.data.model

data class Contact(
    val icon: String,
    val name: String,
    val category: String,
    val phone: String,
    val address: String = ""
)
