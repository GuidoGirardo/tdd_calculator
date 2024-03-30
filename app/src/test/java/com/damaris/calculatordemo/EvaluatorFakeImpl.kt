package com.damaris.calculatordemo

import com.damaris.calculatordemo.data.IEvaluator
import com.damaris.calculatordemo.domain.ResultWrapper

class EvaluatorFakeImpl: IEvaluator {
    internal var succeed: Boolean = true
    internal var called: Boolean = false
    override suspend fun evaluateExpression(exp: String): ResultWrapper<Exception, String> {
        called = true
        return if(succeed) ResultWrapper.build{ VALID_ANSWER }
        else ResultWrapper.build { throw Exception() }
    }

}