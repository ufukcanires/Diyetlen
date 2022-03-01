package com.dogus.dieatcook.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val database_name1 = "KullanicilarinBilgileri"
val table_name1 = "Bilgiler"
val col_adsoyad = "adisoyad"
val col_yas = "yasi"
val col_cinsiyet = "cinsiyet"
val col_boy = "boy"
val col_kilo = "kilo"
val col_boyun = "boyun"
val col_bel = "bel"
val col_id1 = "id"
val col_kalca = "kalca"
val col_tarih = "tarih"
val col_bodyMass = "bodyMass"
val col_bodyFat = "bodyFat"
val col_basalGraphs = "basalGraphs"

class ProfilDataBaseHelper(var context: Context) : SQLiteOpenHelper(context, database_name1,null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        var createdTable = " CREATE TABLE "+ table_name1+"("+
                col_id1 +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                col_adsoyad +" VARCHAR(256),"+
                col_yas +" INTEGER,"+
                col_cinsiyet +" VARCHAR(6),"+
                col_boy +" FLOAT,"+
                col_kilo +" FLOAT,"+
                col_boyun +" INTEGER,"+
                col_bel +" INTEGER,"+
                col_kalca +" INTEGER,"+
                col_tarih +" VARCHAR(40),"+
                col_bodyMass +" FLOAT,"+
                col_bodyFat +" FLOAT,"+
                col_basalGraphs +" FLOAT)"

        db?.execSQL(createdTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertedDats(kullanici: Kullanici){
        val database = this.writableDatabase
        val contentv = ContentValues()
        contentv.put(col_adsoyad,kullanici.adsoyad)
        contentv.put(col_yas,kullanici.yas)
        contentv.put(col_cinsiyet,kullanici.cinsiyet)
        contentv.put(col_boy,kullanici.boy)
        contentv.put(col_kilo,kullanici.kilo)
        contentv.put(col_boyun,kullanici.boyun)
        contentv.put(col_bel,kullanici.bel)
        contentv.put(col_kalca,kullanici.kalca)
        contentv.put(col_tarih,kullanici.tarih)
        contentv.put(col_bodyMass,kullanici.bodyMass)
        contentv.put(col_bodyFat,kullanici.bodyFat)
        contentv.put(col_basalGraphs,kullanici.basalGraphs)
        var result = database.insert(table_name1,null,contentv)
        if(result == (-1).toLong()){
            Toast.makeText(context,"Hatalı",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context,"Başarılı",Toast.LENGTH_LONG).show()
        }
    }

    fun readedData() : MutableList<Kullanici>{
        var list : MutableList<Kullanici> = ArrayList()
        val database = this.readableDatabase
        var query = "Select * from "+ table_name1
        var result = database.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                var kullanici = Kullanici()
                kullanici.id = result.getString(result.getColumnIndex(col_id1)).toInt()
                kullanici.adsoyad = result.getString(result.getColumnIndex(col_adsoyad))
                kullanici.yas = result.getString(result.getColumnIndex(col_yas)).toInt()
                kullanici.cinsiyet = result.getString(result.getColumnIndex(col_cinsiyet))
                kullanici.boy = result.getFloat(result.getColumnIndex(col_boy))
                kullanici.kilo = result.getFloat(result.getColumnIndex(col_kilo))
                kullanici.boyun = result.getString(result.getColumnIndex(col_boyun)).toInt()
                kullanici.bel = result.getString(result.getColumnIndex(col_bel)).toInt()
                kullanici.kalca = result.getString(result.getColumnIndex(col_kalca)).toInt()
                kullanici.tarih = result.getString(result.getColumnIndex(col_tarih))
                kullanici.bodyMass = result.getFloat(result.getColumnIndex(col_bodyMass))
                kullanici.bodyFat = result.getFloat(result.getColumnIndex(col_bodyFat))
                kullanici.basalGraphs = result.getFloat(result.getColumnIndex(col_basalGraphs))
                list.add(kullanici)
            }while (result.moveToNext())
        }
        result.close()
        database.close()
        return list
    }
}