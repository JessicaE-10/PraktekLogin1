package com.example.prakteklogin1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper // <--

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        dbHelper = DatabaseHelper(this) // <---

        val inputUsername = findViewById<EditText>(R.id.inputUsername)
        val inputUmur = findViewById<EditText>(R.id.inputUmur)

        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        btnSubmit.setOnClickListener(){

            val username = inputUsername.text.toString()
            val umur = inputUmur.text.toString().toIntOrNull() ?: 0

            val result = dbHelper.checkLogin(username, umur)
            val user: User? = dbHelper.getUser(username, umur)

            if (user != null){
                // ini jika benar
                val intentDashboard = Intent(this, DashboardActivity::class.java)
                intentDashboard.putExtra("nama_lengkap", user.nama_lengkap)
                intentDashboard.putExtra("id", user.id)
                startActivity(intentDashboard)
            } else {
                // ini jika salah
                Toast.makeText(applicationContext, "USERNAME/UMUR SALAH", Toast.LENGTH_LONG).show() //warning saat input username dan umur
            }
        }


        val btnDaftar = findViewById<Button>(R.id.btnDaftar)
        btnDaftar.setOnClickListener(){
            val intentPendaftaran = Intent(this, PendaftaranActivity::class.java)
            startActivity(intentPendaftaran)
        }
    }
}