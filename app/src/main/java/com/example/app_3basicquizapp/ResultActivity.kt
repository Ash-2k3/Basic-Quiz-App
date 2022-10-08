package com.example.app_3basicquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

         val tvScore = findViewById<TextView>(R.id.score)
         val tvUserName = findViewById<TextView>(R.id.user_name)
         val btnFinish = findViewById<Button>(R.id.btn_finish)

        tvUserName.text = intent.getStringExtra(Constants.USER_NAME)
        val totalQuestion = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWER,0)
        tvScore.text  = "Your score is $correctAnswers out of $totalQuestion"
        btnFinish.setOnClickListener(){
            startActivity(Intent(this,MainActivity::class.java))
        }






    }
}