package com.task.core.di

import com.task.core.data.data_source.NetworkApi
import com.task.core.data.repository.ChannelRepositoryImpl
import com.task.core.domain.repository.ChannelRepository
import com.task.core.util.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChannelModule {

    @Provides
    @Singleton
    fun provideNetworkApi(): NetworkApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkApi::class.java)

    }

    @Provides
    @Singleton
    fun provideChannelRepository(networkApi: NetworkApi): ChannelRepository {
        return ChannelRepositoryImpl(networkApi)
    }
}