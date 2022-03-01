package com.dogus.dieatcook.ui.cook.web

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.dogus.dieatcook.databinding.ActivityYemekSepetiBinding
import kotlinx.android.synthetic.main.activity_web.*

class YemekSepetiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityYemekSepetiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYemekSepetiBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.webViewApp.webViewClient = WebViewClient()

        webViewApp.loadUrl("https://www.yemeksepetibanabi.com")
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