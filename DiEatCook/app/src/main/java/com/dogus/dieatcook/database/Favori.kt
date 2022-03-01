package com.dogus.dieatcook.database

class Favori {
    var id : Int = 0
    var food_name : String = ""
    var food_cal : Float = 0F
    constructor(food_name:String,food_cal:Float){
        this.food_name = food_name
        this.food_cal = food_cal
    }
    constructor(){
    }
}