package com.example.hospitalsystem.features.receptionist.di

import com.example.hospitalsystem.features.receptionist.data.datasource.ReceptionstApiCalls
import com.example.hospitalsystem.features.receptionist.data.datasource.ReceptionstRemoteDataSource
import com.example.hospitalsystem.features.receptionist.data.datasource.ReceptionstRemoteDataSourceInterface
import com.example.hospitalsystem.features.receptionist.data.repository.ReceptionstRepository
import com.example.hospitalsystem.features.receptionist.domain.repository.ReceptionstRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ReceptionstModule {

    @Provides
    fun getReceptionstRemoteDataSource (
        receptionstApiCalls: ReceptionstApiCalls
    ): ReceptionstRemoteDataSourceInterface {
        return ReceptionstRemoteDataSource(receptionstApiCalls)
    }

    @Provides
    fun getReceptionstRepository(
        receptionstRemoteDataSourceInterface: ReceptionstRemoteDataSourceInterface
    ): ReceptionstRepositoryInterface {
        return ReceptionstRepository(receptionstRemoteDataSourceInterface)
    }

}