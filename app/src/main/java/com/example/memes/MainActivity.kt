package com.example.memes

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonformeme: Button = findViewById(R.id.button_for_meme)
        buttonformeme.setOnClickListener {
            val intent = Intent(this, Loginactivity::class.java)
            startActivity(intent)
            finish()
            val textshare: TextView = findViewById(R.id.textshare)


        }
    }
}