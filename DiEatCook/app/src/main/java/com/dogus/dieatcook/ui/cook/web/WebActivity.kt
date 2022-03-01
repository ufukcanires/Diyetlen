package com.dogus.dieatcook.ui.cook.web

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.dogus.dieatcook.R
import com.dogus.dieatcook.databinding.ActivityWebBinding
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {

    private lateinit var binding : ActivityWebBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.webViewApp.webViewClient = WebViewClient()

        webViewApp.loadUrl("https://www.trendyol.com")
        webViewApp.settings.javaScriptEnabled = true
        webViewApp.settings.setSupportZoom(true)

    }

    override fun onBackPressed() {
        if(webViewApp.canGoBack()){
            webViewApp.goBack()
        }else{
            super.onBackPressed()
        }
    }
}