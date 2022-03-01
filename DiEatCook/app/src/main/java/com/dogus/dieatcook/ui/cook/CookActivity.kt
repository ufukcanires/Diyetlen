package com.dogus.dieatcook.ui.cook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dogus.dieatcook.adapter.RecyclerViewAdapterCook
import com.dogus.dieatcook.databinding.ActivityCookBinding
import com.dogus.dieatcook.model.CookModel
import com.dogus.dieatcook.service.FoodAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CookActivity : AppCompatActivity(),RecyclerViewAdapterCook.Listener {

    private lateinit var binding : ActivityCookBinding
    private val BASE_URL = "https://api.npoint.io/"
    private var recyclerViewAdapterCook : RecyclerViewAdapterCook? = null
    private var cookModels: ArrayList<CookModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCookBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val layoutManager : RecyclerView.LayoutManager = GridLayoutManager(this,2)
        binding.recyclerView.layoutManager = layoutManager


      binding.searchView1.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                recyclerViewAdapterCook?.filter?.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                recyclerViewAdapterCook?.filter?.filter(newText)
                return true
            }

        })

        loadData()
    }

    private fun loadData() {
        val okHttpClıent = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClıent)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(FoodAPI::class.java)
        val call = service.getCook()

        call.enqueue(object : Callback<List<CookModel>>{
            override fun onResponse(
                call: Call<List<CookModel>>,
                response: Response<List<CookModel>>
            )
            {
            if(response.isSuccessful){
                response.body()?.let {
                    cookModels = ArrayList(it)

                    cookModels?.let {
                        recyclerViewAdapterCook= RecyclerViewAdapterCook(it,this@CookActivity)
                        binding.recyclerView.adapter = recyclerViewAdapterCook
                    }
                }
              }
            }
            override fun onFailure(call: Call<List<CookModel>>, t: Throwable) {
               t.printStackTrace()
            }
        })
    }

    override fun onItemClick(cook: CookModel) {
        val intent = Intent(applicationContext, CookDetailActivity::class.java)
        intent.putExtra("cook",cook)
        startActivity(intent)
    }
}