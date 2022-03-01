package com.dogus.dieatcook.ui.cook.web

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.dogus.dieatcook.databinding.ActivityGetirBinding
import kotlinx.android.synthetic.main.activity_web.*

class GetirActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGetirBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetirBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.webViewApp.webViewClient = WebViewClient()

        webViewApp.loadUrl("https://getir.com")
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