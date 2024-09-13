// ConvertDateUseCaseTest.kt
package com.example.umraguru.domain.usecase

import com.example.umraguru.domain.model.DateConversion
import com.example.umraguru.domain.repository.UmraGuruRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class ConvertDateUseCaseTest {
    private lateinit var repository: UmraGuruRepository
    private lateinit var useCase: ConvertDateUseCase

    @Before
    fun setUp() {
        repository = mockk()
        useCase = ConvertDateUseCase(repository)
    }

    @Test
    fun `invoke with valid date string returns correct DateConversion`() {
        // Given
        val input = "1990-01-01"
        val expectedConversion = DateConversion(33, 396, 11880, 1990)
        every { repository.convertDateToAge(LocalDate.parse(input)) } returns expectedConversion

        // When
        val result = useCase(input)

        // Then
        assertEquals(expectedConversion, result)
    }

    @Test
    fun `invoke with valid age returns correct DateConversion`() {
        // Given
        val input = "30"
        val currentYear = LocalDate.now().year
        val birthYear = currentYear - 30
        val expectedConversion = DateConversion(30, 360, 10800, birthYear)
        every { repository.convertAgeToBirthYear(30) } returns birthYear
        every { repository.convertDateToAge(LocalDate.of(birthYear, 1, 1)) } returns expectedConversion

        // When
        val result = useCase(input)

        // Then
        assertEquals(expectedConversion, result)
    }
}

