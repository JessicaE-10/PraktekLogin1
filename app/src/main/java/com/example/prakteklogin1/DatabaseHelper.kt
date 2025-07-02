package com.example.prakteklogin1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, MY_DATABASE, null, DATABASE_VERSION) {
    companion object {
        private const val MY_DATABASE = "MYDB"
        private const val DATABASE_VERSION = 2
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT, nama_lengkap TEXT, umur INT, username TEXT, password TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {
            db?.execSQL("ALTER TABLE user ADD COLUMN umur INTEGER")
        }
    }

    fun insertUser(nama_lengkap: String, umur: Int, username: String, password: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("nama_lengkap", nama_lengkap)
            put("umur", umur)
            put("username", username)
            put("password", password)
        }

        val result = db.insert("user", null, values)

        if (result == -1L) {
            return false;
        } else {
            return true
        }
    }

    fun checkLogin(username: String, umur: Int): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM user WHERE username = ? AND umur = ?"
        val cursor = db.rawQuery(query, arrayOf(username, umur.toString()))
        val result = cursor.count > 0

        if(result == true) {
            return true
        }else{
            return false
        }
    }

    fun getUser(id: Int): User? { // overriding
        val db = readableDatabase
        val query = "SELECT * FROM user WHERE id = ?"
        val cursor = db.rawQuery(query, arrayOf(id.toString()))

        var user: User? = null
        if (cursor.moveToFirst()) {
            val userId = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val nama_lengkap = cursor.getString(cursor.getColumnIndexOrThrow("nama_lengkap"))
            val umur = cursor.getInt(cursor.getColumnIndexOrThrow("umur"))
            val username = cursor.getString(cursor.getColumnIndexOrThrow("username"))
            val password = cursor.getString(cursor.getColumnIndexOrThrow("password"))
            user = User(userId, nama_lengkap, umur, username, password)
        }
        cursor.close()
        return user
    }


    fun getUser(username: String, umur: Int): User? {
        val db = readableDatabase
        val query = "SELECT * FROM user WHERE username = ? AND umur = ?"
        val cursor = db.rawQuery(query, arrayOf(username, umur.toString()))

        var user: User? = null
        if (cursor.moveToFirst()) {
            val userId = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val nama_lengkap = cursor.getString(cursor.getColumnIndexOrThrow("nama_lengkap"))
            val umur = cursor.getInt(cursor.getColumnIndexOrThrow("umur"))
            val username = cursor.getString(cursor.getColumnIndexOrThrow("username"))
            val password = cursor.getString(cursor.getColumnIndexOrThrow("password"))
            user = User(userId, nama_lengkap, umur, username, password)
        }
        return user
    }




    fun updateUser(id: String, username: String, nama_lengkap: String, umur: Int, password: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("username", username)
            put("nama_lengkap", nama_lengkap)
            put("umur", umur)
            put("password", password)
        }

        val result = db.update(
            "user",
            values,
            "id = ?",       // WHERE clause
            arrayOf(id)                 // WHERE args
        )

        if(result > 0){
            return true
        } else{
            return false
        }
    }
}