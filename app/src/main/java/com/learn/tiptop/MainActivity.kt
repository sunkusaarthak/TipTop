package com.learn.tiptop

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.learn.tiptop.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil
import java.lang.NumberFormatException


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calcButton.setOnClickListener(View.OnClickListener {
            binding.textAmount.clearFocus()
            if(binding.textAmount.text.toString().toDoubleOrNull() == null)
            {
                binding.textTip.text = getString(R.string.errorText)
                return@OnClickListener
            }
            val costOfService = binding.textAmount.text.toString().toDouble()
            val percent = when(binding.radioGroup.checkedRadioButtonId)
            {
                R.id.amazing -> 0.20
                R.id.good -> 0.18
                else -> 0.15
            }
            var tip = costOfService * percent
            if(binding.switchTip.isChecked)
            {
                tip = ceil(tip)
            }
            val numberFormatter = NumberFormat.getCurrencyInstance()
            val formattedTip = numberFormatter.format(tip)
            binding.textTip.text = getString(R.string.tip_amount, formattedTip)
            it.hideKeyboard()
        })
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            binding.textAmount.clearFocus()
        }
        return super.dispatchTouchEvent(ev)
    }
}