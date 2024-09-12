package com.example.hospitalsystem.features.login.di

import com.example.hospitalsystem.features.login.data.datasource.remote.LoginApiCalls
import com.example.hospitalsystem.features.login.data.datasource.remote.LoginRemoteDataSource
import com.example.hospitalsystem.features.login.data.datasource.remote.LoginRemoteDataSourceInterface
import com.example.hospitalsystem.features.login.data.repository.LoginRepository
import com.example.hospitalsystem.features.login.domain.repository.LoginRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object LoginModule {

    @Provides
    fun getLoginRemoteDataSource (
        loginApiCalls: LoginApiCalls
    ): LoginRemoteDataSourceInterface {
        return LoginRemoteDataSource(loginApiCalls)
    }

    @Provides
    fun getLoginRepository (
        loginRemoteDataSourceInterface: LoginRemoteDataSourceInterface
    ): LoginRepositoryInterface {
        return LoginRepository(loginRemoteDataSourceInterface)
    }

}