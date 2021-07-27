package com.example.covaccine

data class Session (
    var name:String,
    var min_age_limit:String,
    var available_capacity:String,
    var available_capacity_dose1:String,
    var available_capacity_dose2:String,
    var vaccine:String,
    var address:String
        )