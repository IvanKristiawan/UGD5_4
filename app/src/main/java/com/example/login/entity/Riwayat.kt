package com.example.login.entity

class Riwayat(var name:String, var date:String) {
    companion object{
        @JvmField
        var listofRiwayat= arrayOf(
            Riwayat("Sia","20-07-2022"),
            Riwayat("Sia","20-07-2022"),
            Riwayat("Sia","20-07-2022"),
            Riwayat("Sia","20-07-2022"),
            Riwayat("Sia","20-07-2022"),
            Riwayat("Sia","20-07-2022")
        )
    }
}