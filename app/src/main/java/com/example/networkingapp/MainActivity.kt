package com.example.networkingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.webkit.WebView
import android.widget.TextView
import java.net.URI

class MainActivity : AppCompatActivity() {
    lateinit var textView: TextView
    lateinit var webView: WebView

    val downloadHandler = Handler(Looper.getMainLooper()) {
        it.obj.toString().let {
            textView.text = it
            webView.loadDataWithBaseURL("", it, "text/html", "UTF-8", null)
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        webView = findViewById(R.id.webView)

        Thread {
            // port implied by protocal
            val url = URI("https://www.temple.edu").toURL()

            // read the entire web page
            val sb: StringBuilder
            url.openStream()
                .bufferedReader().apply {
                    sb = StringBuilder()
                    while (readLine().let {
                        sb.append(it).append("\n")
                        it != null
                    });
                }
            val websiteContent = sb.toString()

            downloadHandler.sendMessage(Message.obtain().apply{obj = websiteContent})

            Log.d("Website", websiteContent)
        }.start()
    }
}