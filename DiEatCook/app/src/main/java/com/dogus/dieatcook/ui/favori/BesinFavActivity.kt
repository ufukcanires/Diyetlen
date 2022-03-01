package com.dogus.dieatcook.ui.favori

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dogus.dieatcook.R
import com.dogus.dieatcook.database.FavoriDataBaseHelper
import com.dogus.dieatcook.databinding.ActivityBesinFavBinding

class BesinFavActivity : AppCompatActivity() {

    lateinit var binding : ActivityBesinFavBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBesinFavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val context = this
        val dbFood = FavoriDataBaseHelper(context)

        binding.deleteFood.setOnClickListener{
            dbFood.deleteFoodData()
            binding.foodName.text = ""
            binding.foodCal.text = ""
        }

        var dataFood = dbFood.readFoodData()
        binding.foodName.text = ""
        binding.foodCal.text = ""
        for(i in 0 until dataFood.size){
            binding.foodName.append(dataFood.get(i).id.toString() + " ) " + dataFood.get(i).food_name + " - " + dataFood.get(i).food_cal+" kcal" +"\n" + "\n")
        }
    }
}