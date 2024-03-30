package com.damaris.calculatordemo.domain

interface ICalculator {

    suspend fun evaluateExpression(exp:String, callback: (ResultWrapper<Exception, String>) -> Unit)

}