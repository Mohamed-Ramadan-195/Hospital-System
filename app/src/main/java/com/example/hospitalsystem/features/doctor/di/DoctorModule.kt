package com.example.hospitalsystem.features.doctor.di

import com.example.hospitalsystem.features.doctor.data.datasource.DoctorApiCalls
import com.example.hospitalsystem.features.doctor.data.datasource.DoctorRemoteDataSource
import com.example.hospitalsystem.features.doctor.data.datasource.DoctorRemoteDataSourceInterface
import com.example.hospitalsystem.features.doctor.data.repository.DoctorRepository
import com.example.hospitalsystem.features.doctor.domain.repository.DoctorRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DoctorModule {

    @Provides
    fun getDoctorRemoteDataSource (doctorApiCalls: DoctorApiCalls)
        : DoctorRemoteDataSourceInterface {
            return DoctorRemoteDataSource(doctorApiCalls)
    }

    @Provides
    fun getDoctorRepository (
        doctorRemoteDataSourceInterface: DoctorRemoteDataSourceInterface
    ): DoctorRepositoryInterface {
        return DoctorRepository(doctorRemoteDataSourceInterface)
    }

}