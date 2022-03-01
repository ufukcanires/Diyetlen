package com.dogus.dieatcook.ui.nutrite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.PopupMenu
import com.dogus.dieatcook.database.Comment
import com.dogus.dieatcook.database.Favori
import com.dogus.dieatcook.database.FavoriDataBaseHelper
import com.dogus.dieatcook.databinding.ActivityNutriteDetailBinding
import com.dogus.dieatcook.model.NutrientModel
import com.dogus.dieatcook.ui.comment.CommentActivity
import com.dogus.dieatcook.ui.cook.web.GetirActivity
import com.dogus.dieatcook.ui.cook.web.WebActivity
import com.dogus.dieatcook.ui.cook.web.YemekSepetiActivity
import com.dogus.dieatcook.ui.favori.BesinFavActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_cook_detail.*
import kotlinx.android.synthetic.main.activity_nutrite_detail.*

class NutriteDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityNutriteDetailBinding
    private var nutriteModels : ArrayList<NutrientModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNutriteDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val selectedFood = intent.getSerializableExtra("data") as NutrientModel

        binding.foodName.text = selectedFood.name
        binding.calTxt.text = selectedFood.calories.toString() + " " + "kcal"
        binding.carbonhydrateTxt.text = selectedFood.carbohydrates_total_g.toString()
        binding.foodDesc.text = selectedFood.desc
        binding.hipTxt.text = selectedFood.fiber_g.toString()
        binding.proteinTxt.text = selectedFood.protein_g.toString()

        val url2 = selectedFood.img_url
        Picasso.get().load(url2).into(binding.foodImg)

        val context = this
        val dbFood = FavoriDataBaseHelper(context)

        binding.favNutrite.setOnClickListener{
            val popupMenu = PopupMenu(this,fav_nutrite)
            popupMenu.menu.add(Menu.NONE,0,0,"BESİNLER")

            popupMenu.setOnMenuItemClickListener {
                val id = it.itemId
                if(id==0){
                    var favori = Favori(selectedFood.name,selectedFood.calories)
                    dbFood.insertFoodData(favori)
                }
                false
            }
            popupMenu.show()
          }
        }
    }