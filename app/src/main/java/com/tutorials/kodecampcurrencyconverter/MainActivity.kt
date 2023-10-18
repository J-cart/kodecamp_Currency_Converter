package com.tutorials.kodecampcurrencyconverter

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tutorials.kodecampcurrencyconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.convertButton.setOnClickListener {
            convertCurrency()
        }
    }


    private fun convertCurrency(){
        val currency = binding.currencyToConvertEditText.text.toString().toDoubleOrNull()

        currency?.let {
            val checkedCurrency = binding.currencyConversionOptions.checkedRadioButtonId
            // these values were gotten from google market summary as at 03/10/2023 - 4:55pm
            val currencyToConvertToValue=when(checkedCurrency){
                R.id.dollar_conversion_option->{
                    767.23
                }
                R.id.pounds_conversion_option->{
                    925.89
                }
                R.id.euro_conversion_option->{
                    802.04
                }
                else->{
                    20_946_955.67
                }
            }
            val currencyToConvertToSymbol = when (checkedCurrency) {
                R.id.dollar_conversion_option -> {
                    "$"
                }

                R.id.pounds_conversion_option -> {
                   "£"
                }

                R.id.euro_conversion_option -> {
                    "€"
                }

                else -> {
                    "₿"
                }
            }

            val newValue =  it / currencyToConvertToValue
            var newValueString = newValue.toString()

            if (binding.roundUpSwitch.isChecked) {
                newValueString = "%.2f".format(newValue)
            }

            binding.conversionResultText.text = getString(
                R.string.conversion_result_text,
                currencyToConvertToSymbol,
                newValueString,
                currencyToConvertToValue.toString()
            )
        } ?: emptyStateValue()
    }

    private fun emptyStateValue(){
        Toast.makeText(this, "Invalid conversion value", Toast.LENGTH_SHORT).show()
        return
    }
}