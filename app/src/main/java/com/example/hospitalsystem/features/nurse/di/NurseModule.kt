package com.example.hospitalsystem.features.nurse.di

import com.example.hospitalsystem.features.nurse.data.datasource.NurseApiCalls
import com.example.hospitalsystem.features.nurse.data.datasource.NurseRemoteDataSource
import com.example.hospitalsystem.features.nurse.data.datasource.NurseRemoteDataSourceInterface
import com.example.hospitalsystem.features.nurse.data.repository.NurseRepository
import com.example.hospitalsystem.features.nurse.domain.repository.NurseRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object NurseModule {

    @Provides
    fun getNurseRemoteDataSource (nurseApiCalls: NurseApiCalls)
        : NurseRemoteDataSourceInterface {
            return NurseRemoteDataSource(nurseApiCalls)
    }

    @Provides
    fun getNurseRepository (nurseRemoteDataSourceInterface: NurseRemoteDataSourceInterface)
        : NurseRepositoryInterface {
            return NurseRepository(nurseRemoteDataSourceInterface)
    }

}