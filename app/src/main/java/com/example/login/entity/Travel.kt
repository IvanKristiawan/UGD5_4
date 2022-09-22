package com.example.login.entity

class Travel(var name:String, var cost:Number) {
    companion object{
        @JvmField
        var listofTravel= arrayOf(
            Travel("Garuda Indonesia",1000),
            Travel("Airline",1000),
            Travel("Merpati", 10000),
        )
    }
}