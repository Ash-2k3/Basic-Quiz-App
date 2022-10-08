package com.example.app_3basicquizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private var progressBar : ProgressBar? = null
    private  var progressBarTv : TextView? = null
    private var tvQuestion : TextView? = null
    private var imgFlag : ImageView? = null
    private  var btnSubmit : Button? = null

    private var userName:String?  = null
    private var correctAnswers = 0

    private var tvOption1: TextView? = null
    private var tvOption2: TextView? = null
    private var tvOption3: TextView? = null
    private var tvOption4: TextView? = null
    private var pageTrigger: Boolean = true

    private var questionsList : ArrayList<Question>? = null
    private var currentQuestion: Int  = 1   // It is to denote in which question I am.One que per Page
    private var selectedOption : Int? = null  // initially Let's keep it Null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        progressBar = findViewById(R.id.progressbar)
        progressBarTv = findViewById(R.id.progress_text)
        tvQuestion = findViewById(R.id.question)
        imgFlag = findViewById(R.id.country_flag)
        tvOption1 = findViewById(R.id.option1)
        tvOption2 = findViewById(R.id.option2)
        tvOption3 = findViewById(R.id.option3)
        tvOption4 = findViewById(R.id.option4)
        btnSubmit = findViewById(R.id.submit_btn)

        userName = intent.getStringExtra(Constants.USER_NAME)

        questionsList = Constants.getQuestions()
//        Log.i("Total Number of Questions are", "${questionsList!!.size}")  // Just to check the working in the Log Console
        tvOption1?.setOnClickListener(this)
        tvOption2?.setOnClickListener(this)
        tvOption3?.setOnClickListener(this)
        tvOption4?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)
        setQuestion() //  To display the question of current page and whole content accordingly
    }

    private fun setQuestion() {
        defaultOptionView()
        val question: Question = questionsList!![currentQuestion - 1]
        tvQuestion?.text = question.question
        imgFlag?.setImageResource(question.image)
        progressBar?.progress = currentQuestion
        progressBarTv?.text = "$currentQuestion/${progressBar?.max}"
        tvOption1?.text = question.optionOne
        tvOption2?.text = question.optionTwo
        tvOption3?.text = question.optionThree
        tvOption4?.text = question.optionFour
        btnSubmit?.text = "SUBMIT"
    }

    private fun defaultOptionView(){
        val options = ArrayList<TextView>()
        tvOption1?.let {
            options.add(0,it)
        }
        tvOption2?.let{
            options.add(1,it)
        }
        tvOption3?.let {
            options.add(2,it)
        }
        tvOption4?.let {
            options.add(3,it)
        }
        for(option in options)
        {
            option.setTextColor(Color.parseColor(  "#CDCBCB"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this , R.drawable.default_background_option)
        }
    }

    private fun selectedOptionView(view : TextView , slcOptnNo : Int){
         defaultOptionView()
        selectedOption = slcOptnNo
        view.setTypeface(view.typeface , Typeface.BOLD)
        view.background = ContextCompat.getDrawable(this,R.drawable.selected_background_option)
    }

    private fun answerView(answer :Int?, view:Int ){
          when(answer){
              0->{
                  tvOption1?.background = ContextCompat.getDrawable(this , view)
              }
              1->{
                  tvOption2?.background = ContextCompat.getDrawable(this , view)
              }
              2->{
                  tvOption3?.background = ContextCompat.getDrawable(this , view)
              }
              3->{
                  tvOption4?.background = ContextCompat.getDrawable(this , view)
              }
          }
    }

    private fun onSubmit(optionSelected: Int? , view: Button?){
        if(!pageTrigger){
            if(questionsList!!.size >= currentQuestion){
            setQuestion()
            pageTrigger = true}
            else{
               val intent = Intent(this , ResultActivity::class.java)
                intent.putExtra(Constants.USER_NAME,userName)
                intent.putExtra(Constants.CORRECT_ANSWER,correctAnswers)
                intent.putExtra(Constants.TOTAL_QUESTIONS,questionsList!!.size)
                startActivity(intent)
                finish()
            }
        }
          else{
        if(selectedOption != null)
        {
            val question = questionsList!![currentQuestion - 1]
            if(selectedOption == question.CorrectAnswer - 1)
            {
                answerView(selectedOption , R.drawable.correct_background_option)
                correctAnswers++
            }
            else{
                answerView(selectedOption , R.drawable.wrong_answer_background)
                answerView(question.CorrectAnswer - 1 , R.drawable.correct_background_option)
            }
            currentQuestion++
            pageTrigger = false
            selectedOption = null
            if(currentQuestion == questionsList!!.size + 1)
            {
                btnSubmit?.text = "FINISH"
            }
            else{
                btnSubmit?.text = "NEXT QUESTION"
            }
        }
        else
        {
            Toast.makeText(this,"Please Select a Option",Toast.LENGTH_SHORT).show()
        }
          }
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.option1 -> {
//                tvOption1?.text="DONE"
                tvOption1?.let{
                     selectedOptionView(it,0
                     )   }
            }
            R.id.option2 -> {
                tvOption2?.let{
                    selectedOptionView(it,1
                    )   }
            }
            R.id.option3 -> {
                tvOption3?.let{
                    selectedOptionView(it,2
                    )   }
            }
            R.id.option4 -> {
                tvOption4?.let{
                    selectedOptionView(it,3
                    )   }
            }
           R.id.submit_btn -> {
               onSubmit(selectedOption , btnSubmit)
           }
        }
    }
}