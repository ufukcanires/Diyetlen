package com.dogus.dieatcook.model

import java.io.Serializable

data class NutrientModel (
    var id : Int,
    var protein_g : Float,
    var carbohydrates_total_g : Float,
    var calories : Float,
    var fiber_g : Float,
    var name : String,
    var desc : String,
    var img_url : String,
    var photo : String
):Serializable