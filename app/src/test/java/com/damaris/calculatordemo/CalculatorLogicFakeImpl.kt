package com.damaris.calculatordemo

import com.damaris.calculatordemo.domain.ResultWrapper
import com.damaris.calculatordemo.ui.ICalculatorUI
import com.damaris.calculatordemo.ui.ViewEvent

class CalculatorLogicFakeImpl : ICalculatorUI.Logic {
    var handleResultCalled = false
    var result: ResultWrapper<Exception, String>? = null
    override fun handleViewEvent(event: ViewEvent) {

    }

    override fun handleResult(result: ResultWrapper<Exception, String>) {
        this.result = result
        handleResultCalled = true
    }

}