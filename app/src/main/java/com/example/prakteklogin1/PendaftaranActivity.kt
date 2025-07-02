package com.example.prakteklogin1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PendaftaranActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private var userId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pendaftaran)

        dbHelper = DatabaseHelper(this)

        val btnKembali = findViewById<Button>(R.id.btnKembali)
        btnKembali.setOnClickListener() {
            val intentLogin = Intent(this, MainActivity::class.java)
            startActivity(intentLogin)
        }

        val inputNamaLengkap = findViewById<EditText>(R.id.inputNamaLengkap)
        val inputUmur = findViewById<EditText>(R.id.inputUmur)
        val inputUsername = findViewById<EditText>(R.id.inputNim)
        val inputPassword = findViewById<EditText>(R.id.inputUmur)

        val btnSimpan = findViewById<Button>(R.id.btnSimpan)

        btnSimpan.setOnClickListener() {

            val nama_lengkap = inputNamaLengkap.text.toString()
            val umur = inputUmur.text.toString().toIntOrNull() ?: 0
            val username = inputUsername.text.toString()
            val password = inputPassword.text.toString()

            val result = dbHelper.insertUser(nama_lengkap, umur, username, password)

            if (result == true) {
                Toast.makeText(
                    this, "Data Berhasil Disimpan",
                    Toast.LENGTH_LONG
                ).show()

                inputNamaLengkap.text.clear()
                inputUmur.text.clear()
                inputUsername.text.clear()
                inputPassword.text.clear()

            } else {
                Toast.makeText(
                    this, "Umur Tidak Boleh Kosong",
                    Toast.LENGTH_LONG
                ).show()

            }
        }

        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        if (intent.hasExtra("id")) {
            val id = intent.getIntExtra("id", 0)
                val user: User? = dbHelper.getUser(id)

                if (user != null) {
                    inputNamaLengkap.setText(user.nama_lengkap)
                    inputUmur.setText(user.umur.toString())
                    inputUsername.setText(user.username)
                    inputPassword.setText(user.password)

                btnSimpan.visibility = View.GONE
                btnUpdate.visibility = View.VISIBLE

            } else {
                btnSimpan.visibility = View.VISIBLE
                btnUpdate.visibility = View.GONE
            }
        }

        btnUpdate.setOnClickListener() {
            val nama_lengkap = inputNamaLengkap.text.toString()
            val umur = inputUmur.text.toString().toIntOrNull() ?: 0
            val username = inputUsername.text.toString()
            val password = inputPassword.text.toString()

            val id = intent.getIntExtra("id", 0)
            val result = dbHelper.updateUser(id.toString(), username, nama_lengkap, umur, password)
            if (result == true){
                Toast.makeText(this, "Data Berhasil Diubah", Toast.LENGTH_LONG).show()
            } else{
                Toast.makeText(this, "Data Gagal Diubah", Toast.LENGTH_LONG).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets}

    }
}