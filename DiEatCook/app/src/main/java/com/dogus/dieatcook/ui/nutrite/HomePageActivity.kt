package com.dogus.dieatcook.ui.nutrite

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dogus.dieatcook.R
import com.dogus.dieatcook.adapter.RecyclerViewAdapterNutrite
import com.dogus.dieatcook.databinding.ActivityHomePageBinding
import com.dogus.dieatcook.model.NutrientModel
import com.dogus.dieatcook.service.FoodAPI
import com.dogus.dieatcook.ui.cook.CookActivity
import com.dogus.dieatcook.ui.favori.BesinFavActivity
import com.dogus.dieatcook.ui.favori.YemekTarifiFavActivity
import com.dogus.dieatcook.ui.graphs.BasalGraphsActivity
import com.dogus.dieatcook.ui.graphs.BodyFatActivity
import com.dogus.dieatcook.ui.graphs.GraphsActivity
import com.dogus.dieatcook.ui.user.ProfilActivity
import com.dogus.dieatcook.ui.user.RegisterActivity
import com.dogus.dieatcook.ui.user.SingInActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home_page.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomePageActivity : AppCompatActivity(), RecyclerViewAdapterNutrite.Listener {

    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding : ActivityHomePageBinding
    private var nutriteModels : ArrayList<NutrientModel>? = null
    private val BASE_URL = "https://api.npoint.io/"
    private var recyclerViewAdapterNutrite : RecyclerViewAdapterNutrite? = null
    private var userName : String ?= null
    lateinit var sharedPreferences: SharedPreferences
    var isRemembered = false

    private fun getIntentExtra(){

        sharedPreferences = getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE)
        isRemembered = sharedPreferences.getBoolean("CHECKBOXS",false)

        userName = intent.getStringExtra("userName")

        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("USER",userName)
        editor.apply()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        getIntentExtra()
        toggle = ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                recyclerViewAdapterNutrite?.filter?.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                recyclerViewAdapterNutrite?.filter?.filter(newText)
                return true
            }

        })

        navView.setNavigationItemSelectedListener {

            it.isChecked = true

            when(it.itemId){
                R.id.nav_home ->{
                    val intent = Intent(this, HomePageActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_food ->{
                val intent = Intent(this, CookActivity::class.java)
                startActivity(intent)
                }
                R.id.nav_account ->{
                 startActivity(Intent(this,ProfilActivity::class.java).putExtra("userName",userName))
                }
                R.id.nav_logOut ->{
                    val intent = Intent(this,SingInActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_graphs -> {
                    val intent = Intent(this,GraphsActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_graphs1 -> {
                    val intent = Intent(this,BasalGraphsActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_graphs2 -> {
                    val intent = Intent(this,BodyFatActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_food_fav -> {
                    val intent = Intent(this,BesinFavActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_cook_fav -> {
                    val intent = Intent(this,YemekTarifiFavActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }

        val layoutManager : RecyclerView.LayoutManager = GridLayoutManager(this,2)
        binding.recyclerView.layoutManager = layoutManager

        loadData()

    }

    private fun loadData() {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(FoodAPI::class.java)
        val call = service.getNutrient()

        call.enqueue(object : Callback<List<NutrientModel>>{
            override fun onResponse(
                call: Call<List<NutrientModel>>,
                response: Response<List<NutrientModel>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        nutriteModels = ArrayList(it)

                        nutriteModels?.let {
                        recyclerViewAdapterNutrite = RecyclerViewAdapterNutrite(it,this@HomePageActivity)
                        binding.recyclerView.adapter = recyclerViewAdapterNutrite
                        }
                    }
                }
            }
            override fun onFailure(call: Call<List<NutrientModel>>, t: Throwable)
            {
                t.printStackTrace()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(nutrition: NutrientModel) {
        val intent = Intent(applicationContext, NutriteDetailActivity::class.java)
        intent.putExtra("data",nutrition)
        startActivity(intent)
    }
}