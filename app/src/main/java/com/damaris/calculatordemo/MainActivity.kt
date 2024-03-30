package com.damaris.calculatordemo

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.damaris.calculatordemo.databinding.ActivityMainBinding
import com.damaris.calculatordemo.ui.ICalculatorUI
import com.damaris.calculatordemo.ui.ViewEvent

class MainActivity : AppCompatActivity(),
    View.OnClickListener,
    View.OnLongClickListener,
    ICalculatorUI.View {

    lateinit var logic: ICalculatorUI.Logic
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindUserInterface()

    }

    private fun bindUserInterface() {
        binding.btnEvaluate.setOnClickListener(this)
        binding.btnDecimal.setOnClickListener(this)
        binding.btnDelete.setOnClickListener(this)
        binding.btnDelete.setOnLongClickListener(this)

        binding.btnZero.setOnClickListener(this)
        binding.btnOne.setOnClickListener(this)
        binding.btnTwo.setOnClickListener(this)
        binding.btnThree.setOnClickListener(this)
        binding.btnFour.setOnClickListener(this)
        binding.btnFive.setOnClickListener(this)
        binding.btnSix.setOnClickListener(this)
        binding.btnSeven.setOnClickListener(this)
        binding.btnEight.setOnClickListener(this)
        binding.btnNine.setOnClickListener(this)

        binding.btnAdd.setOnClickListener(this)
        binding.btnSubtract.setOnClickListener(this)
        binding.btnMultiply.setOnClickListener(this)
        binding.btnDivide.setOnClickListener(this)
    }

    companion object {
        val DISPLAY_STATE = "STATE"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState?.putString(DISPLAY_STATE, binding.lblDisplay.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.lblDisplay.text = savedInstanceState?.getString(DISPLAY_STATE, "")
    }

    /* ----- INTERFACE FUNCTIONS ----- */

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnEvaluate -> logic.handleViewEvent(ViewEvent.Evaluate)
            R.id.btnDelete -> logic.handleViewEvent(ViewEvent.Delete)
            else -> {
                if(v is Button){
                    logic.handleViewEvent(ViewEvent.Input(v.text.toString()))
                }
            }
        }
    }

    override fun onLongClick(v: View?): Boolean {
        when(v?.id){
            R.id.btnDelete -> logic.handleViewEvent(ViewEvent.DeleteAll)
        }
        return true // no queremos que despues se ejecute el onClick
    }

    override var display: String
        get() = binding.lblDisplay.text.toString()
        set(value) {
            binding.lblDisplay.text = value
        }

    override fun showError(value: String) {
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show()
    }
}