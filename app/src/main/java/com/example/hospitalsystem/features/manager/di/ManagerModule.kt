package com.example.hospitalsystem.features.manager.di

import com.example.hospitalsystem.features.manager.data.datasource.ManagerApiCalls
import com.example.hospitalsystem.features.manager.data.datasource.ManagerRemoteDataSource
import com.example.hospitalsystem.features.manager.data.datasource.ManagerRemoteDataSourceInterface
import com.example.hospitalsystem.features.manager.data.repository.ManagerRepository
import com.example.hospitalsystem.features.manager.domain.repository.ManagerRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ManagerModule {

    @Provides
    fun getManagerRemoteDataSource (
        managerApiCalls: ManagerApiCalls
    ): ManagerRemoteDataSourceInterface {
        return ManagerRemoteDataSource(managerApiCalls)
    }

    @Provides
    fun getManagerRepository (
        managerRemoteDataSourceInterface: ManagerRemoteDataSourceInterface
    ): ManagerRepositoryInterface {
        return ManagerRepository(managerRemoteDataSourceInterface)
    }

}