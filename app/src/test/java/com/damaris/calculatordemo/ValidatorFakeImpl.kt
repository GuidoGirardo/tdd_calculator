package com.damaris.calculatordemo

import com.damaris.calculatordemo.data.IValidator

class ValidatorFakeImpl : IValidator {
    internal var succeed: Boolean = true
    internal var called: Boolean = false
    override suspend fun validateExpression(exp: String): Boolean {
        called = true
        return succeed
    }

}