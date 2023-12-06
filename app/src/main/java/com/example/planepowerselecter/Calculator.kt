package com.example.planepowerselecter

class Calculator {
    fun weightToPower(inputWeight:Int):Int {
        var requiredPower = ((inputWeight * 80) / 453.6).toInt()
        return requiredPower
    }
}