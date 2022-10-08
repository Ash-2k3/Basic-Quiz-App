package com.example.app_3basicquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val startBtn = findViewById<Button>(R.id.start_Btn)
        val nameText = findViewById<EditText>(R.id.user_name)
        startBtn.setOnClickListener(){
            if (nameText.text.isEmpty()){
                Toast.makeText(this,"Name cannot be blank Buddy!",Toast.LENGTH_SHORT).show()
            }
            else {
                val intent = Intent(this,QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME,nameText.text.toString())
                startActivity(intent)
                finish()


            }
        }

    }

}