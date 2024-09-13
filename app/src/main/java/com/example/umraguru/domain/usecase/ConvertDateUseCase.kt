// ConvertDateUseCase.kt
package com.example.umraguru.domain.usecase

import com.example.umraguru.domain.model.DateConversion
import com.example.umraguru.domain.repository.UmraGuruRepository
import java.time.LocalDate
import javax.inject.Inject

class ConvertDateUseCase @Inject constructor(
    private val repository: UmraGuruRepository
) {
    operator fun invoke(input: String): DateConversion {
        return when {
            input.toIntOrNull() != null -> {
                val birthYear = repository.convertAgeToBirthYear(input.toInt())
                repository.convertDateToAge(LocalDate.of(birthYear, 1, 1))
            }
            else -> {
                val date = LocalDate.parse(input)
                repository.convertDateToAge(date)
            }
        }
    }
}