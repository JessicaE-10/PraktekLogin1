package com.example.prakteklogin1

data class User(
    val id: Int,
    val nama_lengkap: String,
    val umur: Int, //Tambah val umur
    val username: String,
    val password: String
)