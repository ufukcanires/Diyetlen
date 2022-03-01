package com.dogus.dieatcook.database

class Comment {
    var id : Int = 0
    var adsoyad : String = ""
    var yorum : String = ""
    constructor(adsoyad: String,yorum: String){
        this.adsoyad = adsoyad
        this.yorum = yorum
    }
    constructor(){
    }
}