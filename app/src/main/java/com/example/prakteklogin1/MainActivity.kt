package com.example.prakteklogin1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val inputUsername = findViewById<EditText>(R.id.inputUsername)
        val inputPassword = findViewById<EditText>(R.id.inputPassword)

        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        btnSubmit.setOnClickListener(){
            if(inputUsername.text.toString().equals("jessica")
                && inputPassword.text.toString().equals("1234")) {
                Toast.makeText(applicationContext, "Login berhasil", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(applicationContext, "Login Gagal", Toast.LENGTH_SHORT)
            }
        }
    }
}