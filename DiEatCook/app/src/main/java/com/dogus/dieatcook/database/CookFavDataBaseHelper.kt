package com.dogus.dieatcook.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val database_name3 = "YemekFavorileri"
val table_name3 = "FavoriYemeklerim"
val col_cookName = "cookName"
val col_cookCal = "cookCal"
val col_id3 = "id"

class CookFavDataBaseHelper (var context: Context) : SQLiteOpenHelper(context, database_name3,null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        var createTableCook =  " CREATE TABLE "+ table_name3+"("+
                col_id3 +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                col_cookName +" VARCHAR(256),"+
                col_cookCal +" FLOAT)"

        db?.execSQL(createTableCook)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertCookData(cookFav: CookFav){
        val dbCook = this.writableDatabase
        val cvCook = ContentValues()
        cvCook.put(col_cookName,cookFav.cook_name)
        cvCook.put(col_cookCal,cookFav.cook_cal)
        var sonucCook = dbCook.insert(table_name3,null,cvCook)
        if(sonucCook == (-1).toLong()){
            Toast.makeText(context,"Yemek Tarifi Eklenemedi. Tekrar Deneyiniz",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context,"Besin Başarıyla Eklendi. Favori Besinlerden Görebilirsiniz",Toast.LENGTH_LONG).show()
        }
    }

    fun readCookData(): MutableList<CookFav>{
        var listCook : MutableList<CookFav> = ArrayList()
        val dbCook = this.readableDatabase
        var sorguCook = "Select * from " + table_name3
        var sonucCook = dbCook.rawQuery(sorguCook,null)
        if(sonucCook.moveToFirst()){
            do {
                var cookFav = CookFav()
                cookFav.id = sonucCook.getString(sonucCook.getColumnIndex(col_id3)).toInt()
                cookFav.cook_name = sonucCook.getString(sonucCook.getColumnIndex(col_cookName))
                cookFav.cook_cal = sonucCook.getString(sonucCook.getColumnIndex(col_cookCal))
                listCook.add(cookFav)
            }while (sonucCook.moveToNext())
        }
        sonucCook.close()
        dbCook.close()
        return listCook
    }

    fun deleteCookData(){
        val dbCookDelete = this.writableDatabase
        dbCookDelete.delete(table_name3,null,null)
        dbCookDelete.close()
    }

}