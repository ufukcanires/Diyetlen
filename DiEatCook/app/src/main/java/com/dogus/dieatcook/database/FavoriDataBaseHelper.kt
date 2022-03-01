package com.dogus.dieatcook.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val database_name2 = "Favorilerim"
val table_name2 = "FavoriBesinlerim"
val col_food_name = "foodName"
val col_food_cal = "foodCal"
val col_id2 = "id"

class FavoriDataBaseHelper(var context: Context) : SQLiteOpenHelper(context, database_name2,null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        var createTableFood =  " CREATE TABLE "+ table_name2+"("+
                col_id2 +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                col_food_name +" VARCHAR(256),"+
                col_food_cal +" FLOAT)"

        db?.execSQL(createTableFood)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertFoodData(favori: Favori){
        val dbFood = this.writableDatabase
        val cvFood = ContentValues()
        cvFood.put(col_food_cal,favori.food_cal)
        cvFood.put(col_food_name,favori.food_name)
        var sonucFood = dbFood.insert(table_name2,null,cvFood)
        if(sonucFood == (-1).toLong()){
            Toast.makeText(context,"Besin Eklenemedi. Tekrar Deneyiniz",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context,"Besin Başarıyla Eklendi. Favori Besinlerden görebilirsiniz",Toast.LENGTH_LONG).show()
        }
    }
    fun readFoodData(): MutableList<Favori>{
        var listFood : MutableList<Favori> = ArrayList()
        val dbFood = this.readableDatabase
        var sorguFood = "Select * from "+ table_name2
        var sonucFood = dbFood.rawQuery(sorguFood,null)
        if(sonucFood.moveToFirst()){
            do {
                var favori = Favori()
                favori.id = sonucFood.getString(sonucFood.getColumnIndex(col_id2)).toInt()
                favori.food_name = sonucFood.getString(sonucFood.getColumnIndex(col_food_name))
                favori.food_cal = sonucFood.getFloat(sonucFood.getColumnIndex(col_food_cal))
                listFood.add(favori)
            }while (sonucFood.moveToNext())
        }
        sonucFood.close()
        dbFood.close()
        return listFood
    }

    fun deleteFoodData(){
        val dbFoodDelete = this.writableDatabase
        dbFoodDelete.delete(table_name2,null,null)
        dbFoodDelete.close()
    }

}