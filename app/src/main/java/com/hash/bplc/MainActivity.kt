package com.hash.bplc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hash.bplc.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateBPwearRate() }
    }

    private fun calculateBPwearRate() {
        val currentBP = binding.bpCurrentMeasurement.text.toString().toDoubleOrNull() ?: return
        val currentMileage =
            binding.currentMileageMeasurement.text.toString().toDoubleOrNull() ?: return
        val previousBP = binding.bpPreviousMeasurement.text.toString().toDoubleOrNull() ?: return
        val previousMileage =
            binding.previousMileageMeasurement.text.toString().toDoubleOrNull() ?: return
        val limitBP = binding.bpLimit.text.toString().toDoubleOrNull() ?: return
        val bpWearRate = (previousBP - currentBP) / (currentMileage - previousMileage)
        val wearMileage = currentMileage + ((currentBP - limitBP) / bpWearRate)
        val bpWearRateTenK = (previousBP - currentBP) * 10000 / (currentMileage - previousMileage)
        val formattedBP = NumberFormat.getNumberInstance().format(bpWearRateTenK)
        val formattedMileage = kotlin.math.ceil(wearMileage)
        binding.bpWearRateResult.text = formattedBP
        binding.bpWearMileageResult.text = formattedMileage.toString()
    }
}