package com.chernybro.wb52.di


import android.content.Context
import android.content.SharedPreferences
import com.chernybro.wb52.data.local.HeroesStorage
import com.chernybro.wb52.data.local.HeroesStorageImpl
import com.chernybro.wb52.data.remote.service.HeroListApi
import com.chernybro.wb52.data.repository.HeroesListRepository
import com.chernybro.wb52.data.repository.HeroesListRepositoryImpl
import com.chernybro.wb52.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }



    @Provides
    @Singleton
    fun provideHeroListApi(okHttpClient: OkHttpClient): HeroListApi {
        return Retrofit.Builder()
            .baseUrl(Constants.HEROES_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HeroListApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHeroesRepository(api: HeroListApi, heroesStorage: HeroesStorage): HeroesListRepository {
        return HeroesListRepositoryImpl(api, heroesStorage)
    }

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("preferences_name", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideHeroesStorage(sharedPreferences: SharedPreferences): HeroesStorage {
        return HeroesStorageImpl(sharedPreferences)
    }
}