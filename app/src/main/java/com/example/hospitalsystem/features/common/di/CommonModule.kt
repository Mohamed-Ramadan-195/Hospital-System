package com.example.hospitalsystem.features.common.di

import com.example.hospitalsystem.features.common.data.datasource.CommonApiCalls
import com.example.hospitalsystem.features.common.data.datasource.CommonRemoteDataSource
import com.example.hospitalsystem.features.common.data.datasource.CommonRemoteDataSourceInterface
import com.example.hospitalsystem.features.common.data.repository.CommonRepository
import com.example.hospitalsystem.features.common.domain.repository.CommonRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object CommonModule {

    @Provides
    fun getCommonRemoteDataSource(
        commonApiCalls: CommonApiCalls
    ): CommonRemoteDataSourceInterface {
        return CommonRemoteDataSource(commonApiCalls)
    }

    @Provides
    fun getCommonRepository(
        commonRemoteDataSourceInterface: CommonRemoteDataSourceInterface
    ): CommonRepositoryInterface {
        return CommonRepository(commonRemoteDataSourceInterface)
    }

}