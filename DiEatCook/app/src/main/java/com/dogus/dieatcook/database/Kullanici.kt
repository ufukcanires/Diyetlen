package com.dogus.dieatcook.database

import java.io.Serializable
import java.util.*

data class Kullanici (
    var id : Int = 0,
    var adsoyad : String = "",
    var yas : Int = 0,
    var cinsiyet : String = "",
    var boy : Float = 0F,
    var kilo : Float = 0F,
    var boyun : Int = 0,
    var bel : Int = 0,
    var kalca : Int = 0,
    var tarih : String = "",
    var bodyMass : Float = 0F,
    var bodyFat : Float = 0F,
    var basalGraphs : Float = 0F
) : Serializable
