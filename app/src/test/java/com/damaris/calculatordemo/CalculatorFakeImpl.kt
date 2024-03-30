package com.damaris.calculatordemo

import com.damaris.calculatordemo.domain.ICalculator
import com.damaris.calculatordemo.domain.ResultWrapper

class CalculatorFakeImpl : ICalculator {

    var succeed: Boolean = false

    override suspend fun evaluateExpression(
        exp: String,
        callback: (ResultWrapper<Exception, String>) -> Unit
    ) {
        if(succeed) callback.invoke(ResultWrapper.build{ "4" })
        else callback.invoke(ResultWrapper.build{ throw Exception() })
    }

}