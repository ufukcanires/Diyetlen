package com.dogus.dieatcook.ui.calculate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dogus.dieatcook.R
import com.dogus.dieatcook.databinding.ActivityBasalMetabolismBinding

class BasalMetabolismActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBasalMetabolismBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasalMetabolismBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.BMI.setOnClickListener {
            if(binding.heightedt.text.isNotEmpty() && binding.weightedt.text.isNotEmpty()) {
                var gender = binding.genderedt.text.toString()
                var age = binding.ageedt.text.toString()
                var weight = binding.weightedt.text.toString()
                var height = binding.heightedt.text.toString()
                if(gender=="Erkek")
                {
                    var result: Double = (66.5 + (13.75 * weight.toFloat() ) + (5.03 * height.toFloat() ) - (6.75 * age.toFloat()))
                    binding.result.setText("Bazal Metabolizma Hızınız:  "  + result)
                }
                else if(gender=="Kadın")
                {
                    var result: Double = (655.1 + (9.56 * weight.toFloat() ) + (1.85 * height.toFloat()) - (4.68 * age.toFloat()))
                    binding.result.setText("Bazal Metabolizma Hızınız:  "  + result)
                }
            }
            else{
                binding.result.setText("Vücut Bilgilerinizi Eksik Girdiniz")
                binding.result.setTextColor(getColor(R.color.black))
            }
        }
    }
}