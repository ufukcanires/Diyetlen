package com.dogus.dieatcook.ui.user

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.dogus.dieatcook.R
import com.dogus.dieatcook.database.Kullanici
import com.dogus.dieatcook.database.ProfilDataBaseHelper
import com.dogus.dieatcook.database.col_yas
import com.dogus.dieatcook.databinding.ActivityUptatedProfileBinding
import java.text.SimpleDateFormat
import java.util.*

class UptatedProfileActivity : AppCompatActivity() {

    private var binding: ActivityUptatedProfileBinding?= null
    lateinit var sharedPreferences: SharedPreferences
    var isRemembered = false
    private var dateFormatter: SimpleDateFormat? = null
    private var calendarHktIlkTarih: Calendar? = null
    private var tarih : String?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this,R.layout.activity_uptated_profile)

        listener()

        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        isRemembered = sharedPreferences.getBoolean("CHECKBOX",false)


        val context = this
        val db = ProfilDataBaseHelper(context)

        binding?.btnUpdate?.setOnClickListener {

            val et_adsoyad = binding?.adsoyad?.text.toString()
            val et_yas = binding?.yas?.text.toString()
            val et_cinsiyet = binding?.cinsiyet?.text.toString()
            val et_boy = binding?.boy?.text.toString()
            val et_kilo = binding?.kilo?.text.toString()
            val et_boyun = binding?.boyun?.text.toString()
            val et_bel = binding?.bel?.text.toString()
            val et_kalca = binding?.kalca?.text.toString()

            if(et_adsoyad.isNotEmpty() && et_yas.isNotEmpty() &&
                    et_cinsiyet.isNotEmpty() && et_boy.isNotEmpty() &&
                et_kilo.isNotEmpty() && et_boyun.isNotEmpty() && tarih.isNullOrEmpty().not() &&
                    et_bel.isNotEmpty() && et_kalca.isNotEmpty()){
                var result = et_kilo.toFloat()/(et_boy.toFloat()*et_boy.toFloat())
                var bodyFatResult = -1*(495 / ( 1.29579 - 0.35004 * Math.log10( (et_bel + et_kalca + et_boyun).toDouble() ) + 0.22100 * Math.log10( et_boy.toDouble()) ) - 450)
                var bodyFatSonuc = bodyFatResult.toFloat()
                var basalResult = 66.5 + (13.75*et_kilo.toFloat() ) + (5.03*et_boy.toFloat() ) - (6.75*et_yas.toFloat())
                var basalGraphsSonuc = basalResult.toFloat()
                var kullanici = Kullanici(0,et_adsoyad,et_yas.toInt(),et_cinsiyet,et_boy.toFloat(),
                    et_kilo.toFloat(),et_boyun.toInt(),et_bel.toInt(),et_kalca.toInt(),tarih!!,result,bodyFatSonuc,basalGraphsSonuc)
                db.insertedDats(kullanici)
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("NAME",et_adsoyad)
                editor.putString("CINSIYET",et_cinsiyet)
                editor.putInt("YAS",et_yas.toInt())
                editor.putFloat("BOY",et_boy.toFloat())
                editor.putFloat("KILO",et_kilo.toFloat())
                editor.putInt("BOYUN",et_boyun.toInt())
                editor.putInt("BEL",et_bel.toInt())
                editor.putInt("KALCA",et_kalca.toInt())
                editor.apply()

                val intent = Intent(applicationContext,ProfilActivity::class.java)
                intent.putExtra("ad",et_adsoyad)
                intent.putExtra("yas",et_yas)
                intent.putExtra("cinsiyet",et_cinsiyet)
                intent.putExtra("boy",et_boy)
                intent.putExtra("kilo",et_kilo)
                intent.putExtra("boyun",et_boyun)
                intent.putExtra("bel",et_bel)
                intent.putExtra("kalca",et_kalca)
                startActivity(intent)
            }else{
                Toast.makeText(applicationContext,"lütfen boş alanları dolduralım",Toast.LENGTH_LONG).show()
            }

        }
    }
    private fun listener(){
        binding?.tarih?.setOnClickListener {
            val takvim = Calendar.getInstance(Locale("tr"))
            val yil = takvim.get(Calendar.YEAR)
            val ay = takvim.get(Calendar.MONTH)
            val gun = takvim.get(Calendar.DAY_OF_MONTH)
            dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale("tr"))

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month1, dayOfMonth ->
                var month = month1
                calendarHktIlkTarih = Calendar.getInstance(Locale("tr"))
                calendarHktIlkTarih?.set(year, month, dayOfMonth)

                month += 1

                binding?.tarih?.text = "$year-$month-$dayOfMonth"

                tarih = "$year-$month-$dayOfMonth"

            }, yil, ay, gun)

            dpd.setButton(DatePickerDialog.BUTTON_POSITIVE, "TAMAM", dpd)
            dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İPTAL", dpd)
            dpd.datePicker.maxDate = takvim.timeInMillis
            dpd.show()
        }
    }


}