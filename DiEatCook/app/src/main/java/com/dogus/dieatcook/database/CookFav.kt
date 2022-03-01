package com.dogus.dieatcook.database

class CookFav {
    var id : Int = 0
    var cook_name : String = ""
    var cook_cal : String = ""
    constructor(cook_name:String,cook_cal:String){
        this.cook_name = cook_name
        this.cook_cal = cook_cal
    }
    constructor(){
    }
}