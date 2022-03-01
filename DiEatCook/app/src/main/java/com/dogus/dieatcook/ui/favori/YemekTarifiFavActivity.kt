package com.dogus.dieatcook.ui.favori

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dogus.dieatcook.R
import com.dogus.dieatcook.database.CookFavDataBaseHelper
import com.dogus.dieatcook.databinding.ActivityYemekTarifiFavBinding

class YemekTarifiFavActivity : AppCompatActivity() {

    lateinit var binding : ActivityYemekTarifiFavBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityYemekTarifiFavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val context = this
        val dbCook = CookFavDataBaseHelper(context)

        binding.deleteCook.setOnClickListener {
            dbCook.deleteCookData()
            binding.foodName.text = ""
            binding.foodCal.text = ""
        }

        var dataCook = dbCook.readCookData()
        binding.foodName.text = ""
        binding.foodCal.text = ""
        for(i in 0 until dataCook.size){
            binding.foodName.append(dataCook.get(i).id.toString() + " ) " + dataCook.get(i).cook_name + " - " + dataCook.get(i).cook_cal +"\n" + "\n")
        }

    }
}