package com.example.hackathon_2k24.domain.di

import android.content.SharedPreferences
import com.example.hackathon_2k24.domain.network.BaseService
import com.example.hackathon_2k24.domain.network.ServiceMethods
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {


    @Provides
    @Singleton
    fun providesBaseService(preference: SharedPreferences) =
        providesRetrofitBuilder(
            baseUrl = ServiceMethods.baseUrl(),
            preference = preference,
            isBaseService = true
        ).create(BaseService::class.java)


    private fun providesRetrofitBuilder(
        baseUrl: String,
        preference: SharedPreferences,
        isBaseService: Boolean,
    ): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val headerChain = Interceptor { chain ->
            val original = chain.request()
            val builder = original.newBuilder()
                .header("Content-Type", "application/json")
                .addHeader("Connection", "close")
                .method(original.method(), original.body())
            chain.proceed(builder.build())
        }

        val certificatePinner = CertificatePinner.Builder()
            .add("vapt.aws.khushibaby.org", "sha256/0wTxIuOd3yYg3tX7/3aTLXOxAWwlWV0NCN3TRNlJHcY=")
            .add("vapt.aws.khushibaby.org", "sha256/vxRon/El5KuI4vx5ey1DgmsYmRY0nDd5Cg4GfJ8S+bg=")
            .add("vapt.aws.khushibaby.org", "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .build()

        val okHttpClient = OkHttpClient.Builder().apply {
//            certificatePinner(certificatePinner)
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS).retryOnConnectionFailure(true)
            addInterceptor(headerChain)
        }.build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

}