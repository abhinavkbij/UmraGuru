package com.example.umraguru

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.umraguru.databinding.ActivityMainBinding
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            val input = binding.inputEditText.text.toString()
            if (input.isNotEmpty()) {
                calculateAge(input)
            } else {
                Toast.makeText(this, "Please enter a date or age", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calculateAge(input: String) {
        try {
            val today = LocalDate.now()
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

            val (years, months, days, yearOfBirth) = when {
                input.toIntOrNull() != null -> {
                    val age = input.toInt()
                    val birthDate = today.minusYears(age.toLong())
                    val period = Period.between(birthDate, today)
                    Quadruple(age, age * 12, age * 365, birthDate.year)
                }
                else -> {
                    val birthDate = LocalDate.parse(input, formatter)
                    val period = Period.between(birthDate, today)
                    Quadruple(
                        period.years,
                        period.years * 12 + period.months,
                        period.toTotalMonths() * 30 + period.days,
                        birthDate.year
                    )
                }
            }

            binding.ageTextView.text = "Age: $years years"
            binding.monthsTextView.text = "Months: $months months"
            binding.daysTextView.text = "Days: $days days"
            binding.yearOfBirthTextView.text = "Year of Birth: $yearOfBirth"
        } catch (e: DateTimeParseException) {
            Toast.makeText(this, "Invalid date format. Please use DD-MM-YYYY", Toast.LENGTH_SHORT).show()
        }
    }

    data class Quadruple<out A, out B, out C, out D>(
        val first: A,
        val second: B,
        val third: C,
        val fourth: D
    )
}