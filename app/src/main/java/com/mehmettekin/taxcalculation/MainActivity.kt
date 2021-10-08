package com.mehmettekin.taxcalculation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mehmettekin.taxcalculation.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            calculation()
        }

    }

    private fun calculation() {

        val incomeString = binding.grossIncomeEditText.text.toString()
        val income = incomeString.toDoubleOrNull()

        if (income == null){
            binding.resultTextView.text = ""
            return
        }

        val selectedRadioButton = binding.rateOptionRadioGroup.checkedRadioButtonId

        val rate = when(selectedRadioButton){
                R.id.twenty_percent_radio_button -> 0.20
                R.id.eighteen_percent_radio_button -> 0.18
                else -> 0.12
        }

        val choosenRadioButton = binding.maritalStatusRadioGroup.checkedRadioButtonId

        val maritalStatus = when(choosenRadioButton){
            R.id.married_radio_button -> 0.01
            else -> 0.03
        }


        var tax = (income*rate) +(income*maritalStatus)

        val rollingUp = binding.roundedNumberUpSwitch.isChecked

        if (rollingUp){
            tax =kotlin.math.ceil(tax)
        }

        val newTax = NumberFormat.getCurrencyInstance(Locale("tr","TR")).format(tax)
        binding.resultTextView.text = getString(R.string.Tax,newTax)

    }


}