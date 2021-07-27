package com.example.covaccine

data class StateCase (
        var State:String,
        var ConfirmedCases:Int,
        var recoveredCases:Int,
        var deathCases:Int,
        var activeCases:Int,
        var newConfirmedCases:Int,
        var newRecoverCases:Int,
        var newDeatCases:Int,
        )
