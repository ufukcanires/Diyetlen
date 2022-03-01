package com.dogus.dieatcook.ui.comment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dogus.dieatcook.database.Comment
import com.dogus.dieatcook.database.DataBaseHelper
import com.dogus.dieatcook.databinding.ActivityCommentBinding

class CommentActivity : AppCompatActivity() {

    lateinit var binding: ActivityCommentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val context = this
        val db = DataBaseHelper(context)

        binding.btnkaydet.setOnClickListener {

            var et_adsoyad = binding.etadsoyad.text.toString()
            var et_comment = binding.etyorum.text.toString()

            if(et_adsoyad.isNotEmpty() && et_comment.isNotEmpty()){
                var comment = Comment(et_adsoyad,et_comment)
                db.insertData(comment)

            }else{
                Toast.makeText(applicationContext,"Lütfen boş alanları dolduralım", Toast.LENGTH_LONG).show()
            }
        }

        binding.btngoster.setOnClickListener {
            var data = db.readData()
            binding.tvsonuc.text = ""
            for(i in 0 until data.size){
                binding.tvsonuc.append(data.get(i).id.toString()+" "
                        + data.get(i).adsoyad+ " - " + data.get(i).yorum + "\n" + "\n")
            }
        }
    }
}