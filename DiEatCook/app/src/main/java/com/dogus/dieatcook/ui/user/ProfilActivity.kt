package com.dogus.dieatcook.ui.user

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.dogus.dieatcook.R
import com.dogus.dieatcook.database.DBHelper
import com.dogus.dieatcook.databinding.ActivityNutriteDetailBinding
import com.dogus.dieatcook.databinding.ActivityProfilBinding
import com.dogus.dieatcook.model.NutrientModel
import org.w3c.dom.Text
import android.net.Uri
import android.os.Build
import android.widget.Toast
import java.util.jar.Manifest


class ProfilActivity : AppCompatActivity() {

    private lateinit var button :Button
    private lateinit var imageView: ImageView
    private lateinit var email : TextView
    private lateinit var adi : TextView
    private lateinit var yas : TextView
    private lateinit var cinsiyet : TextView
    private lateinit var boy : TextView
    private lateinit var kilo : TextView
    private lateinit var boyun : TextView
    private lateinit var bel : TextView
    private lateinit var body_mass : TextView
    private lateinit var body_fat : TextView
    private lateinit var basal_meto : TextView
    private lateinit var kalca : TextView
    private lateinit var updatebutton :Button

    lateinit var preferences: SharedPreferences
    lateinit var preferencess: SharedPreferences

    companion object
    {
        val IMAGE_REQUEST_CODE = 100
        private val IMAGE_PICK_CODE = 1000;
        //Permission code
        private val PERMISSION_CODE = 1001;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)


        preferences = getSharedPreferences("SHARED_PREF",Context.MODE_PRIVATE)
        preferencess = getSharedPreferences("SHARED_PREFS",Context.MODE_PRIVATE)


        button = findViewById(R.id.changePhoto)
        imageView = findViewById(R.id.img_save)
        email = findViewById(R.id.changeEmail)
        val emails = preferencess.getString("USER","")
        email.text=emails
        updatebutton = findViewById(R.id.update_profil)
        adi = findViewById(R.id.adi)
        yas = findViewById(R.id.yas)
        cinsiyet = findViewById(R.id.cinsiyet)
        boy = findViewById(R.id.boy)
        kilo = findViewById(R.id.kilo)
        boyun = findViewById(R.id.boyun)
        bel = findViewById(R.id.bel)
        kalca = findViewById(R.id.kalca)
        body_mass = findViewById(R.id.body_mass)
        body_fat = findViewById(R.id.body_fat)
        basal_meto = findViewById(R.id.basal_meta)

        button.setOnClickListener{
            pickImageGallery()
        }

        val name = preferences.getString("NAME","GİRİLMEDİ")
        adi.text=name

        val gender = preferences.getString("CINSIYET","GİRİLMEDİ")
        cinsiyet.text=gender

        val age = preferences.getInt("YAS",0)
        yas.text=""+age

        val height = preferences.getFloat("BOY",0F)
        boy.text=""+height

        val weight = preferences.getFloat("KILO", 0F)
        kilo.text=""+weight

        val neck = preferences.getInt("BOYUN",0)
        boyun.text=""+neck

        val waist = preferences.getInt("BEL",0)
        bel.text=""+waist

        val hip = preferences.getInt("KALCA",0)
        kalca.text=""+hip

       val img = preferences.getString("image","")
        if(img?.isNotEmpty() == true){
           val uri = Uri.parse(img)
       imageView.setImageURI(uri)
       }

        var result : Float = (weight / (height * height))
        if (result < 18.5F) {
            body_mass.setText("Zayıfsınız")
        } else if (result >= 18.5F && result <= 24.9F) {
            body_mass.setText("Normal Kilolusunuz")
        } else if (result >= 25F && result <= 29.9F) {
            body_mass.setText("Fazla Kilolusunuz")
        } else if (result >= 30F && result <= 39.9F) {
            body_mass.setText("Malesef Obezsiniz")
        } else {
            body_mass.setText("İleri derecede obezsiniz")
        }

        var basal = (66.5 + (13.75 * weight ) + (5.03 * height ) - (6.75 * age))
        basal_meto.setText(basal.toString())


        body_fat.setText("Yağ Oranınız Normal")

        updatebutton.setOnClickListener {
            val intent = Intent(applicationContext,UptatedProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun pickImageGallery() {
       if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
           if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
            val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE);
               requestPermissions(permissions,PERMISSION_CODE);
           }else{
               pickImageFromGallery()
           }
       }else{
           pickImageFromGallery()
       }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent,IMAGE_PICK_CODE)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size >0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    pickImageFromGallery()
                }
                else{
                    //permission from popup denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val editor: SharedPreferences.Editor = preferences.edit()
        if(requestCode == IMAGE_PICK_CODE && resultCode == Activity.RESULT_OK){
            if(data?.data != null){
                imageView.setImageURI(data?.data)
               editor.putString("image",data?.data.toString()).apply()
            }
        }
    }
}