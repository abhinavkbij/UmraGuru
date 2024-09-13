// DateConverterRepository.kt
package com.example.umraguru.domain.repository

import com.example.umraguru.domain.model.DateConversion
import java.time.LocalDate

interface UmraGuruRepository {
    fun convertDateToAge(date: LocalDate): DateConversion
    fun convertAgeToBirthYear(age: Int): Int
}