package com.example.hospitalsystem.di

import com.example.hospitalsystem.features.analysis.data.datasource.AnalysisApiCalls
import com.example.hospitalsystem.features.common.data.datasource.CommonApiCalls
import com.example.hospitalsystem.features.doctor.data.datasource.DoctorApiCalls
import com.example.hospitalsystem.features.hr.data.datasource.HrApiCalls
import com.example.hospitalsystem.features.login.data.datasource.remote.LoginApiCalls
import com.example.hospitalsystem.features.manager.data.datasource.ManagerApiCalls
import com.example.hospitalsystem.features.nurse.data.datasource.NurseApiCalls
import com.example.hospitalsystem.features.receptionist.data.datasource.ReceptionstApiCalls
import com.example.hospitalsystem.utils.BASE_URL
import com.example.hospitalsystem.utils.SharedPreferenceDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
            .connectTimeout(50, TimeUnit.SECONDS)
            .writeTimeout(150, TimeUnit.SECONDS)
            .readTimeout(50, TimeUnit.SECONDS)
            .callTimeout(50, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(
                object : Interceptor {
                    override fun intercept(chain: Interceptor.Chain): Response {
                        val originalRequest = chain.request()
                        val originalUrl = originalRequest.url
                        val url = originalUrl.newBuilder().build()
                        val requestBuilder = originalRequest.newBuilder().url(url)
                            .addHeader("Accept", "application/json")
                            .addHeader("Authorization", "Bearer ${SharedPreferenceDatabase.getAccessToken()}"
                            )
                        val request = requestBuilder.build()
                        val response = chain.proceed(request)
                        response.code//status code
                        return response
                }
            })
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun getLoginApiCalls(retrofit: Retrofit): LoginApiCalls {
        return retrofit.create(LoginApiCalls::class.java)
    }

    @Singleton
    @Provides
    fun getHrApiCalls(retrofit: Retrofit): HrApiCalls {
        return retrofit.create(HrApiCalls::class.java)
    }

    @Singleton
    @Provides
    fun getReceptionstApiCalls(retrofit: Retrofit): ReceptionstApiCalls {
        return retrofit.create(ReceptionstApiCalls::class.java)
    }

    @Singleton
    @Provides
    fun getDoctorApiCalls(retrofit: Retrofit): DoctorApiCalls {
        return retrofit.create(DoctorApiCalls::class.java)
    }

    @Singleton
    @Provides
    fun getNurseApiCalls(retrofit: Retrofit): NurseApiCalls {
        return retrofit.create(NurseApiCalls::class.java)
    }

    @Singleton
    @Provides
    fun getCommonApiCalls(retrofit: Retrofit): CommonApiCalls {
        return retrofit.create(CommonApiCalls::class.java)
    }

    @Singleton
    @Provides
    fun getManagerApiCalls(retrofit: Retrofit): ManagerApiCalls {
        return retrofit.create(ManagerApiCalls::class.java)
    }

    @Singleton
    @Provides
    fun getAnalysisApiCalls(retrofit: Retrofit): AnalysisApiCalls {
        return retrofit.create(AnalysisApiCalls::class.java)
    }
}