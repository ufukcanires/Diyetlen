package com.dogus.dieatcook.model

import java.io.Serializable

data class CookModel (

    var id : Int,
    var food_name : String,
    var description : String,
    var carbohydrate : String,
    var protein : String,
    var oil : String,
    var fiber : Double,
    var preparation_time : String,
    var cooking_time : String,
    var person : Int,
    var ingredients : List<String>,
    var food_calori : String,
    var img_url : String,
    var notes : String
    ): Serializable