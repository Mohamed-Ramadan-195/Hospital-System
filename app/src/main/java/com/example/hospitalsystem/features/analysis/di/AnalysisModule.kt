package com.example.hospitalsystem.features.analysis.di

import com.example.hospitalsystem.features.analysis.data.datasource.AnalysisApiCalls
import com.example.hospitalsystem.features.analysis.data.datasource.AnalysisRemoteDataSource
import com.example.hospitalsystem.features.analysis.data.datasource.AnalysisRemoteDataSourceInterface
import com.example.hospitalsystem.features.analysis.data.repository.AnalysisRepository
import com.example.hospitalsystem.features.analysis.domain.repository.AnalysisRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object AnalysisModule {

    @Provides
    fun getAnalysisRemoteDataSource (
        analysisApiCalls: AnalysisApiCalls
    ): AnalysisRemoteDataSourceInterface {
        return AnalysisRemoteDataSource(analysisApiCalls)
    }

    @Provides
    fun getAnalysisRepository (
        analysisRemoteDataSourceInterface: AnalysisRemoteDataSourceInterface
    ): AnalysisRepositoryInterface {
        return AnalysisRepository(analysisRemoteDataSourceInterface)
    }

}