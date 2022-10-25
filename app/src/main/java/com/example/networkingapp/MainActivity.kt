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
            // port implied by protocal
            val url = URI("https://www.temple.edu").toURL()

            // read the entire web page
            val sb: StringBuilder
            url.openStream()
                .bufferedReader().apply {
                    sb = StringBuilder()
                    while (readLine().let {
                        sb.append(it)
                        it != null
                    })
                }
            val websiteContent = sb.toString()

            Log.d("Website", websiteContent)
        }
    }
}