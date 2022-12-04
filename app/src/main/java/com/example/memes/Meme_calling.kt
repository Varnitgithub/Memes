package com.example.memes

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class Meme_calling : AppCompatActivity() {
var currentimage:String? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meme_calling)
        val buttonfornext: Button = findViewById(R.id.buttonfornext)
        val buttonforshare: Button = findViewById(R.id.buttonforshare)


        buttonforshare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "i share this meme $currentimage")
            val chooser = Intent.createChooser(intent, "share this meme by using ")
            startActivity(chooser)
        }
        buttonfornext.setOnClickListener {

            memeshare()
        }


    }

    @SuppressLint("SuspiciousIndentation")
    fun memeshare() {
        val progressBar:ProgressBar = findViewById(R.id.progessbar)
        progressBar.visibility = View.VISIBLE
        val queue = Volley.newRequestQueue(this)
        val url = " https://meme-api.com/gimme"
        val imageview: ImageView = findViewById(R.id.imageview)
        val jsonrequest = JsonObjectRequest(Request.Method.GET,url, null, { response ->

          currentimage = response.getString("url")
            Glide.with(this).load(currentimage).listener(object:RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.GONE
                    return false
                }
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.GONE
                    return false
                }


            }).into(imageview)

        }, { error->
            Toast.makeText(this, "there is an error", Toast.LENGTH_SHORT).show()
        })
        queue.add(jsonrequest)
    }
}