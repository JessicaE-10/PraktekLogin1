package com.example.prakteklogin1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        val tvNamalengkap = findViewById<TextView>(R.id.tvNamaLengkap)
        val nama_lengkap = intent.getStringExtra("nama_lengkap")

        tvNamalengkap.setText(nama_lengkap)

        val id = intent.getIntExtra("id", 0)
        val btnLihatData = findViewById<Button>(R.id.btnLihatData)
        btnLihatData.setOnClickListener(){
            val intentEdit = Intent(this, PendaftaranActivity::class.java)
            intentEdit.putExtra("id", id)
            startActivity(intentEdit)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}