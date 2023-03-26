package com.example.foody.di

import com.example.foody.data.network.FoodRecipesApi
import com.example.foody.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient():OkHttpClient{
        return OkHttpClient().newBuilder()
            .readTimeout(15 , TimeUnit.SECONDS)
            .connectTimeout(15 , TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideConverterFactory():GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory , okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideFoodRecipeApi(retrofit: Retrofit) : FoodRecipesApi {
        return retrofit.create(FoodRecipesApi::class.java)
    }


}