package com.dogus.dieatcook.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val database_name = "Veritabanim"
val table_name = "Kullanicilar"
val col_name = "adisoyad"
val col_comment = "yorumlar"
val col_id = "id"

class DataBaseHelper(var context: Context) : SQLiteOpenHelper(context, database_name,null,1){

    override fun onCreate(db: SQLiteDatabase?) {
        //veritabanı oluştuğunda bir kez çalışır.
        var createTable = " CREATE TABLE "+ table_name+"("+
                col_id +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                col_name +" VARCHAR(256),"+
                col_comment +" VARCHAR(256))"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //Veritabanını yükseltmek için kullanılır.
    }

    //Veri kaydetmek için fonksiyon tanımlıyoruz.
    fun insertData(comment: Comment){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(col_name,comment.adsoyad)
        cv.put(col_comment,comment.yorum)
        var sonuc = db.insert(table_name,null,cv)
        if(sonuc == (-1).toLong()){
            Toast.makeText(context,"Hatalı",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context,"Başarılı",Toast.LENGTH_LONG).show()
        }
    }

    //Verileri okumak için fonksiyon yazıyoruz.
    fun readData(): MutableList<Comment>{
        var liste : MutableList<Comment> = ArrayList()
        val db = this.readableDatabase
        var sorgu = "Select * from "+ table_name
        var sonuc = db.rawQuery(sorgu,null)
        if(sonuc.moveToFirst()){
            do {
                var comment = Comment()
                comment.id = sonuc.getString(sonuc.getColumnIndex(col_id)).toInt()
                comment.adsoyad = sonuc.getString(sonuc.getColumnIndex(col_name))
                comment.yorum = sonuc.getString(sonuc.getColumnIndex(col_comment))
                liste.add(comment)
            }while (sonuc.moveToNext())
        }
        sonuc.close()
        db.close()
        return liste
    }

}