package com.example.shestore.Hilt

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Returns the application context.
     * */
    @Singleton
    @Provides
    fun getApplicationContext(@ApplicationContext context: Context) : Context = context
}