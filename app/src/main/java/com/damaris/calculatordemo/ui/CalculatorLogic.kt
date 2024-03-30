package com.damaris.calculatordemo.ui

import com.damaris.calculatordemo.domain.ICalculator
import com.damaris.calculatordemo.domain.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CalculatorLogic(
    val view: ICalculatorUI.View,
    val calc: ICalculator,
    val dispatcher: CoroutineDispatcher
) : ICalculatorUI.Logic,
    CoroutineScope {

    override fun handleViewEvent(event: ViewEvent) {
        when(event){
            is ViewEvent.Evaluate -> evaluateExpression()
            is ViewEvent.Delete -> deleteChar()
            is ViewEvent.DeleteAll -> view.display = ""
            is ViewEvent.Input -> view.display += event.input
        }
    }

    private fun deleteChar() {
        val currentExpression = view.display
        if(currentExpression.isNotEmpty()) view.display = currentExpression.dropLast(1)
    }

    private fun evaluateExpression() = launch {
        calc.evaluateExpression(view.display, ::handleResult)
    }

    override fun handleResult(result: ResultWrapper<Exception, String>) {
        when(result){
            is ResultWrapper.Success -> view.display = result.value
            is ResultWrapper.Error -> view.showError(GENERIC_ERROR_MESSAGE)
        }
    }

    protected val jobTracker: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = dispatcher + jobTracker

}