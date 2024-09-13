// MainViewModelTest.kt
package com.example.umraguru.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.umraguru.domain.model.DateConversion
import com.example.umraguru.domain.usecase.ConvertDateUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var convertDateUseCase: ConvertDateUseCase
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        convertDateUseCase = mockk()
        viewModel = MainViewModel(convertDateUseCase)
    }

    @Test
    fun `convertDate updates dateConversion LiveData`() {
        // Given
        val input = "1990-01-01"
        val expectedConversion = DateConversion(33, 396, 11880, 1990)
        every { convertDateUseCase(input) } returns expectedConversion

        // When
        viewModel.convertDate(input)

        // Then
        assertEquals(expectedConversion, viewModel.dateConversion.value)
        verify { convertDateUseCase(input) }
    }
}