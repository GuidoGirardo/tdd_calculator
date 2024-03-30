package com.damaris.calculatordemo.data

interface IValidator {

    suspend fun validateExpression(exp:String): Boolean

}