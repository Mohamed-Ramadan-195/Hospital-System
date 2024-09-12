package com.example.hospitalsystem.features.hr.di

import com.example.hospitalsystem.features.hr.data.datasource.HrApiCalls
import com.example.hospitalsystem.features.hr.data.datasource.HrRemoteDataSource
import com.example.hospitalsystem.features.hr.data.datasource.HrRemoteDataSourceInterface
import com.example.hospitalsystem.features.hr.data.repository.HrRepository
import com.example.hospitalsystem.features.hr.domain.repository.HrRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object HrModule {

    @Provides
    fun getHrRemoteDataSource(hrApiCalls: HrApiCalls): HrRemoteDataSourceInterface {
        return HrRemoteDataSource(hrApiCalls)
    }

    @Provides
    fun getHrRepository (
        hrRemoteDataSourceInterface: HrRemoteDataSourceInterface
    ): HrRepositoryInterface {
        return HrRepository(
            hrRemoteDataSourceInterface)
    }

}