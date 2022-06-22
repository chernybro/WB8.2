package com.chernybro.wb52.di

import com.chernybro.wb52.presentation.navigation.GeneralCiceroneHolder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NavigationModule {

    @Provides
    @Singleton
    fun provideGeneralCiceroneHolder(): GeneralCiceroneHolder {
        return GeneralCiceroneHolder()
    }

}