package com.dogus.dieatcook.ui.guest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dogus.dieatcook.databinding.ActivityGuestBinding
import com.dogus.dieatcook.ui.calculate.BasalMetabolismActivity
import com.dogus.dieatcook.ui.calculate.BodyFatRatioActivity
import com.dogus.dieatcook.ui.calculate.BodyMassIndexActivity

class GuestActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGuestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.calculate1.setOnClickListener{
            val intent = Intent(applicationContext, BodyMassIndexActivity::class.java)
            startActivity(intent)
        }

        binding.calculate2.setOnClickListener{
            val intent = Intent(applicationContext, BasalMetabolismActivity::class.java)
            startActivity(intent)
        }

        binding.calculate3.setOnClickListener {
            val intent = Intent(applicationContext, BodyFatRatioActivity::class.java)
            startActivity(intent)
        }
    }
}