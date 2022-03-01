package com.dogus.dieatcook.ui.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.dogus.dieatcook.R
import com.dogus.dieatcook.database.DBHelper
import com.dogus.dieatcook.ui.nutrite.HomePageActivity
import com.dogus.dieatcook.ui.guest.GuestActivity

class SingInActivity : AppCompatActivity() {

    lateinit var DB : DBHelper
    private lateinit var username : EditText
    private lateinit var password : EditText
    private lateinit var signin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_in)

        DB = DBHelper(this)
        signin = findViewById(R.id.signin1)
        username = findViewById(R.id.username1)
        password = findViewById(R.id.password1)

        signin.setOnClickListener{
            val user = username.text.toString()
            val pass = password.text.toString()

            if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)){
            Toast.makeText(applicationContext,"Lütfen Boşlukları Doldurun!!",Toast.LENGTH_SHORT).show()
            }else{
                val checkuserpass = DB.checkusernamepassword(user,pass)
                if(checkuserpass==true){
                    Toast.makeText(applicationContext,"Giriş Başarılı + ${user}",Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, HomePageActivity::class.java)
                    intent.putExtra("userName",username.text.toString())
                    startActivity(intent)
                }else{
                    Toast.makeText(applicationContext,"Giriş Başarısız",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    fun RegisterClick(view: android.view.View) {
        val intent = Intent(applicationContext, RegisterActivity::class.java)
        startActivity(intent)
    }

    fun guestEnter(view: android.view.View) {
        val intent = Intent(applicationContext, GuestActivity::class.java)
        startActivity(intent)
    }


}