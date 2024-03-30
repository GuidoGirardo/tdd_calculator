package com.damaris.calculatordemo

import com.damaris.calculatordemo.ui.CalculatorLogic
import com.damaris.calculatordemo.ui.ViewEvent
import kotlinx.coroutines.Dispatchers
import org.junit.Test

import org.junit.Assert.*

class CalculatorLogicTest {

    private lateinit var viewFake: CalculatorViewFakeImpl
    private lateinit var calcFake: CalculatorFakeImpl
    private val dispatcher = Dispatchers.Unconfined

    lateinit var calculatorLogic: CalculatorLogic

    @Test
    fun `On Evaluate event succes`() {
        viewFake = CalculatorViewFakeImpl()
        calcFake = CalculatorFakeImpl()
        calcFake.succeed = true

        calculatorLogic = CalculatorLogic(viewFake, calcFake, dispatcher)

        calculatorLogic.handleViewEvent(ViewEvent.Evaluate)

        assertTrue(viewFake.getDisplayCalled)
        assertTrue(viewFake.setDisplayCalled)
    }
}