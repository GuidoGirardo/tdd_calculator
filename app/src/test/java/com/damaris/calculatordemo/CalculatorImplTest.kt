package com.damaris.calculatordemo

import com.damaris.calculatordemo.data.CalculatorImpl
import com.damaris.calculatordemo.data.IEvaluator
import com.damaris.calculatordemo.data.IValidator
import com.damaris.calculatordemo.domain.ResultWrapper
import com.damaris.calculatordemo.ui.ICalculatorUI
import com.damaris.calculatordemo.ui.ViewEvent
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

internal const val VALID_EXPRESSION: String = "2+2"
internal const val INVALID_EXPRESSION: String = "2+++"
internal const val VALID_ANSWER: String = "4"

class CalculatorImplTest {

    private val validatorFake = ValidatorFakeImpl()
    private val evaluatorFake = EvaluatorFakeImpl()
    private val logicFake = CalculatorLogicFakeImpl()

    private lateinit var calc: CalculatorImpl

    @Test
    fun `On Evaluate Valid Expression`() = runBlocking {
        calc = CalculatorImpl(validatorFake, evaluatorFake)
        logicFake.result = null

        calc.evaluateExpression(VALID_EXPRESSION, logicFake::handleResult)

        val result = logicFake.result

        if(result is ResultWrapper.Success){
            assertEquals(result.value, VALID_ANSWER)
        }else{
            assertTrue(false)
        }

    }

    @Test
    fun `On Evaluate Invalid Expression`() = runBlocking {
        calc = CalculatorImpl(validatorFake, evaluatorFake)
        logicFake.result = null
        validatorFake.succeed = false

        calc.evaluateExpression(INVALID_EXPRESSION, logicFake::handleResult)

        val result = logicFake.result

        if(result is ResultWrapper.Error){
            assertTrue(true)
        }else{
            assertTrue(false)
        }

    }

}