package com.dogus.dieatcook.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dogus.dieatcook.R
import com.dogus.dieatcook.ui.user.SingInActivity

class SplahScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splah_screen)
        val background = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(3000)
                    val intent = Intent(
                        baseContext,
                        SingInActivity::class.java
                    )
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()

                }
            }

        }
        background.start()
    }
}