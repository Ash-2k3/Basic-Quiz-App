package com.example.app_3basicquizapp

object Constants  {
    const val USER_NAME : String = "user_name"
    const val CORRECT_ANSWER : String = "correct_answers"
    const val TOTAL_QUESTIONS : String = "total_questions"

    fun getQuestions():ArrayList<Question>{
        val questionsList = ArrayList<Question>()
        val questionOne = Question(1,"Which Country's Flag is this ?",R.drawable.australia,"Australia","China","New Zealand",
                "Romania",1)
        val questionTwo = Question(1,"Which Country's Flag is this ?",R.drawable.canada,"Australia","Canada","Luxemborg",
            "Romania",2)
        val questionThree = Question(1,"Which Country's Flag is this ?",R.drawable.dubai,"China","Dubai","New Zealand",
            "Zimbabwe",2)
        val questionFour = Question(1,"Which Country's Flag is this ?",R.drawable.india,"India","China","New Zealand",
            "Ireland",1)
        val questionFive = Question(1,"Which Country's Flag is this ?",R.drawable.japan,"Japan","China","Malaysia",
            "Sri Lanka",1)
        val questionSix = Question(1,"Which Country's Flag is this ?",R.drawable.kenya,"Zimbabwe","Republic of Congo","Kenya",
            "Egypt",3)
        val questionSeven = Question(1,"Which Country's Flag is this ?",R.drawable.luxemborg,"France","Luxemborg","Switzerland",
            "Romania",2)
        questionsList.add(questionOne)
        questionsList.add(questionTwo)
        questionsList.add(questionThree)
        questionsList.add(questionFour)
        questionsList.add(questionFive)
        questionsList.add(questionSix)
        questionsList.add(questionSeven)

        return questionsList
    }
}