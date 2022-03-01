package com.dogus.dieatcook.service

import com.dogus.dieatcook.model.CookModel
import com.dogus.dieatcook.model.NutrientModel
import retrofit2.Call
import retrofit2.http.GET

interface FoodAPI {

    //https://www.npoint.io/docs

        //Nutrient API
        @GET("6afb385b653e67d49697")
        fun getNutrient() : Call<List<NutrientModel>>

        //Cook API
        @GET("0b151ac962db5791d971")
        fun getCook() : Call<List<CookModel>>
}