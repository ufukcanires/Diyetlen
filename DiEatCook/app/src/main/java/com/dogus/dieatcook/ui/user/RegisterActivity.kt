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

class RegisterActivity : AppCompatActivity() {

    lateinit var DB : DBHelper
    private lateinit var username : EditText
    private lateinit var password : EditText
    private lateinit var repassword : EditText
    private lateinit var signup : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        DB = DBHelper(this)
        signup = findViewById(R.id.signup)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        repassword = findViewById(R.id.repassword)


        signup.setOnClickListener{
            val user = username.text.toString()
            val pass = password.text.toString()
            val repass = repassword.text.toString()

            if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass)){
                Toast.makeText(applicationContext,"Lütfen Boşlukları Doldurun!!",Toast.LENGTH_SHORT).show()
            }else{
                if(pass.equals(repass)){
                    val checkUser = DB.checkusername(user)
                    if(checkUser==false){
                        val insert = DB.insertData(user,pass)
                        if(insert==true){
                            Toast.makeText(applicationContext,"Kayıt Başarılı + ${username.text.toString()}",Toast.LENGTH_SHORT).show()
                            val intent = Intent(applicationContext, HomePageActivity::class.java)
                            intent.putExtra("userName",username.text.toString())
                            startActivity(intent)
                        }else{
                        Toast.makeText(applicationContext,"Kayıt Başarısız",Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(applicationContext,"Mevcut Kullanıcı!!",Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(applicationContext,"Şifre Eşleşmiyor",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun SignInClick(view: android.view.View) {
        val intent = Intent(applicationContext, SingInActivity::class.java)
        startActivity(intent)
    }
}