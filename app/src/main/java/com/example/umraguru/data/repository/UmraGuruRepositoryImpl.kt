// DateConverterRepositoryImpl.kt
package com.example.umraguru.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.umraguru.domain.model.DateConversion
import com.example.umraguru.domain.repository.UmraGuruRepository
import java.time.LocalDate
import java.time.Period
import javax.inject.Inject

class UmraGuruRepositoryImpl @Inject constructor() : UmraGuruRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun convertDateToAge(date: LocalDate): DateConversion {
        val now = LocalDate.now()
        val period = Period.between(date, now)
        return DateConversion(
            years = period.years,
            months = period.years * 12 + period.months,
            days = period.toTotalMonths() * 30 + period.days, // Approximation
            yearOfBirth = date.year
        )
    }

    override fun convertAgeToBirthYear(age: Int): Int {
        return LocalDate.now().year - age
    }
}