package com.example.spaceflightnews.di

import com.example.spaceflightnews.network.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * a Module class for initiating  retrofit and the DefaultRepository
 * which I used to inject in other class
 */

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {
    val BASE_URL = "https://test.spaceflightnewsapi.net/api/"

    @Singleton
    @Provides
    fun provideNewsApi(): NewsApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()
        .create(NewsApi::class.java)



}