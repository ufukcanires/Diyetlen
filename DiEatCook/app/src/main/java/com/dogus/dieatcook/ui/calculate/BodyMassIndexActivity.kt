package com.dogus.dieatcook.ui.calculate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dogus.dieatcook.R
import com.dogus.dieatcook.databinding.ActivityBodyMassIndexBinding

class BodyMassIndexActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBodyMassIndexBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBodyMassIndexBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.calculatebodymass.setOnClickListener {
            if(binding.heightedt.text.isNotEmpty() && binding.weightedt.text.isNotEmpty()) {
                var weight = binding.weightedt.text.toString()
                var height = binding.heightedt.text.toString()
                var result: Float = (weight.toFloat() / (height.toFloat() * height.toFloat()))
                if (result < 18.5) {
                    binding.result.setText("Vücut Kitle Endeksi Değeriniz : " + result + " " + "Zayıfsınız")
                } else if (result >= 18.5 && result <= 24.9) {
                    binding.result.setText("Vücut Kitle Endeksi Değeriniz : " + result + " " + "Normal Kilolusunuz")
                } else if (result >= 25 && result <= 29.9) {
                    binding.result.setText("Vücut Kitle Endeksi Değeriniz : " + result + " " + "Fazla Kilolusunuz")
                } else if (result >= 30 && result <= 39.9) {
                    binding.result.setText("Vücut Kitle Endeksi Değeriniz : " + result + " " + "Malesef Obezsiniz")
                } else {
                    binding.result.setText("Vücut Kitle Endeksi Değeriniz : " + result + " " + "İleri derecede obezsiniz")
                }
            }
            else{
                binding.result.setText("Vücut Bilgilerinizi Eksik Girdiniz")
                binding.result.setTextColor(getColor(R.color.black))
            }
        }


    }


}