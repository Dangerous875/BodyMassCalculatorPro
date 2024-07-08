package com.example.imcapp.data.service

import com.example.imcapp.data.local.Gender

class IMCService {

    companion object {
        var currentWeight = 70
        var currentAge = 30
        var currentGender = Gender.NONE
        var currentHeight = 1.20f
        var resultIMC = 0.0f

        fun calculateIMC(){
            val result = roundToTwoDecimals(currentWeight / (currentHeight * currentHeight))
            resultIMC = result.toFloat()
        }

        private fun roundToTwoDecimals(number: Float): String {
            return String.format("%.2f", number)
        }
    }




}