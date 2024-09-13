// AppModule.kt
package com.example.umraguru.di

import com.example.umraguru.data.repository.UmraGuruRepositoryImpl
import com.example.umraguru.domain.repository.UmraGuruRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindUmraGuruRepository(
        umraGuruRepositoryImpl: UmraGuruRepositoryImpl
    ): UmraGuruRepository
}