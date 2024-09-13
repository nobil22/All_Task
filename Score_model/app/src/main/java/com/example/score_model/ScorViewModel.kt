package com.example.score_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScorViewModel:ViewModel() {
    private var s1=MutableLiveData<Int>()
    val score1:MutableLiveData<Int> get() = s1

    private var s2=MutableLiveData<Int>()
    val score2:MutableLiveData<Int> get() = s2

    fun incrementscore1(){
        val currentscore1=s1.value?:0
        s1.value=currentscore1
    }
    fun incrementscore2(){
        val currentscore2=s2.value?:0
        s2.value=currentscore2
    }
}