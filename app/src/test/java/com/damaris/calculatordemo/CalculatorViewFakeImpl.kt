package com.damaris.calculatordemo

import com.damaris.calculatordemo.ui.ICalculatorUI

class CalculatorViewFakeImpl : ICalculatorUI.View {

    var setDisplayCalled: Boolean = false
    var getDisplayCalled: Boolean = false
    var showErrorCalled: Boolean = false
    var expression: String = ""

    override var display: String
        get() {
            getDisplayCalled = true
            return expression
        }
        set(value) {
            setDisplayCalled = true
            expression = value
        }

    override fun showError(value: String) {
        showErrorCalled = true
    }

}