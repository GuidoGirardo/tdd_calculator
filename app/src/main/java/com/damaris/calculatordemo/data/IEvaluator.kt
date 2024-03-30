package com.damaris.calculatordemo.data

import com.damaris.calculatordemo.domain.ResultWrapper

interface IEvaluator {

    suspend fun evaluateExpression(exp:String): ResultWrapper<Exception, String>

}