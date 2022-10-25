package com.example.networkingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.net.URI

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Thread {
            val url = URI("temple.edu").toURL()

            val websiteContent = url.openStream()
                .bufferedReader().readLine()

            Log.d("Website", websiteContent)
        }
    }
}