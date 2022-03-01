package com.dogus.dieatcook.ui.cook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.PopupMenu
import com.dogus.dieatcook.database.CookFav
import com.dogus.dieatcook.database.CookFavDataBaseHelper
import com.dogus.dieatcook.database.Favori
import com.dogus.dieatcook.database.FavoriDataBaseHelper
import com.dogus.dieatcook.databinding.ActivityCookDetailBinding
import com.dogus.dieatcook.model.CookModel
import com.dogus.dieatcook.ui.comment.CommentActivity
import com.dogus.dieatcook.ui.cook.web.GetirActivity
import com.dogus.dieatcook.ui.cook.web.WebActivity
import com.dogus.dieatcook.ui.cook.web.YemekSepetiActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_cook_detail.*
import kotlinx.android.synthetic.main.activity_nutrite_detail.*

class CookDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCookDetailBinding
    private var cookModels : ArrayList<CookModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCookDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val selectedCook = intent.getSerializableExtra("cook") as CookModel

        val url3 = selectedCook.img_url
        Picasso.get().load(url3).into(binding.foodImg)
        binding.foodName.text = selectedCook.food_name
        binding.calTxt.text = selectedCook.food_calori
        binding.carbonhydrateTxt.text = selectedCook.carbohydrate
        binding.proteinTxt.text = selectedCook.protein
        binding.hipTxt.text = selectedCook.fiber.toString()
        binding.oilTxt.text = selectedCook.oil

        binding.personTxt.text = selectedCook.person.toString()
        binding.prepareTimeTxt.text = selectedCook.preparation_time
        binding.cookingTimeTxt.text = selectedCook.cooking_time
        binding.foodCook.text = selectedCook.description
        binding.foodIngredients.text = selectedCook.ingredients.toString()
        binding.foodNote.text = selectedCook.notes

        binding.comment.setOnClickListener{

            val intent = Intent(applicationContext, CommentActivity::class.java)
            startActivity(intent)
        }

        val context = this
        val dbCook = CookFavDataBaseHelper(context)

        binding.favCook.setOnClickListener {
            val popupMenu = PopupMenu(this,favCook)
            popupMenu.menu.add(Menu.NONE,0,0,"YEMEK TARİFİ")

            popupMenu.setOnMenuItemClickListener {
                val id = it.itemId
                if(id==0){
                    var favoriCook = CookFav(selectedCook.food_name,selectedCook.food_calori)
                    dbCook.insertCookData(favoriCook)
                }
                false
            }
            popupMenu.show()
        }

        binding.btnNeedIng.setOnClickListener {
            val popupMenu = PopupMenu(this,btnNeedIng)
            popupMenu.menu.add(Menu.NONE,0,0,"GETİR")
            popupMenu.menu.add(Menu.NONE,1,0,"TRENDYOL")
            popupMenu.menu.add(Menu.NONE,2,0,"YEMEKSEPETİ BANA Bİ")

        popupMenu.setOnMenuItemClickListener {
            val id = it.itemId
            if(id==0){
                val intent = Intent(applicationContext, GetirActivity::class.java)
                startActivity(intent)
            }
            if(id==1){
                val intents = Intent(applicationContext, WebActivity::class.java)
                startActivity(intents)
            }
            if(id==2){
                val intents = Intent(applicationContext, YemekSepetiActivity::class.java)
                startActivity(intents)
            }
            false
        }
            popupMenu.show()
        }

    }
}