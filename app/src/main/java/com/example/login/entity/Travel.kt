package com.example.login.entity

class Travel(var name:String, var cost:Number) {
    companion object{
        @JvmField
        var listofTravel= arrayOf(
            Travel("Garuda Indonesia",1000),
            Travel("Airline",1000),
            Travel("Merpati", 10000),
            Travel("Indonesia",1000),
            Travel("Lion",1000),
            Travel("Citilink", 10000),
            Travel("Adam Air",1000),
            Travel("Emirate Airways",1000),
            Travel("Super Air Jet", 10000)
        )
    }
}