package com.dogus.dieatcook.ui.calculate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dogus.dieatcook.R
import com.dogus.dieatcook.databinding.ActivityBodyFatRatioBinding

class BodyFatRatioActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBodyFatRatioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBodyFatRatioBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.calculatebodymass.setOnClickListener {
            if(binding.heightedt.text.isNotEmpty() &&
                    binding.neckedt.text.isNotEmpty() &&
                    binding.waistedt.text.isNotEmpty() && binding.gender.text.isNotEmpty()) {
                if(binding.gender.text.toString() == "Kadın"){
                    var height = binding.heightedt.text.toString().toFloat()
                    var waist = binding.waistedt.text.toString().toFloat()
                    var hip = binding.hipedt.text.toString().toFloat()
                    var neck = binding.neckedt.text.toString().toFloat()
                    var result: Double = 495 / ( 1.29579 - 0.35004 * Math.log10( (waist + hip - neck).toDouble() ) + 0.22100 * Math.log10( height.toDouble() ) ) - 450
                    if (result < 25) {
                        binding.result.setText("Vücut Yağ Oranı Değeriniz : " + result + " " + " ")
                    } else if (result >=25 && result <= 31) {
                        binding.result.setText("Vücut Yağ Oranı Değeriniz : " + result + " " + "Yağ Oranınız Normal")
                    } else if (result > 31) {
                        binding.result.setText("Vücut Yağ Oranı Değeriniz : " + result + " " + "Yağ Oranınız Fazla")
                    }
                }else(binding.gender.text.toString() == "Erkek")
                var height = binding.heightedt.text.toString().toFloat()
                var waist = binding.waistedt.text.toString().toFloat()
                var neck = binding.neckedt.text.toString().toFloat()
                var result: Double = 495 / ( 1.0324 - 0.19077 * Math.log10( (waist - neck).toDouble() ) + 0.15456 * Math.log10( height.toDouble() ) ) - 450
                if (result < 18) {
                    binding.result.setText("Vücut Yağ Oranı Değeriniz : " + result + " " + " ")
                } else if (result >=18 && result <= 24) {
                    binding.result.setText("Vücut Yağ Oranı Değeriniz : " + result + " " + "Yağ Oranınız Normal")
                } else if (result > 24) {
                    binding.result.setText("Vücut Yağ Oranı Değeriniz : " + result + " " + "Yağ Oranınız Fazla")
                }
            }
            else{
                binding.result.setText("Vücut Bilgilerinizi Eksik Girdiniz")
                binding.result.setTextColor(getColor(R.color.black))
            }
        }

    }
}