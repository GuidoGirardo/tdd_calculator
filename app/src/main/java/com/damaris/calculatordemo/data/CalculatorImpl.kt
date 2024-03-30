package com.damaris.calculatordemo.data

import com.damaris.calculatordemo.domain.ICalculator
import com.damaris.calculatordemo.domain.ResultWrapper

class CalculatorImpl(val validator: IValidator,
    val evaluator: IEvaluator): ICalculator {
    override suspend fun evaluateExpression(
        exp: String,
        callback: (ResultWrapper<Exception, String>) -> Unit
    ) {
        if(validator.validateExpression(exp)) callback.invoke(
            evaluator.evaluateExpression(exp)
        )else callback.invoke(ResultWrapper.build { throw Exception() })
    }

}