package com.example.studyhelper.di

import android.content.Context
import com.example.studyhelper.data.local.AppDatabase
import com.example.studyhelper.data.local.StudyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    @Singleton // Use Singleton se o DAO for stateless
    fun provideStudyDao(appDatabase: AppDatabase): StudyDao {
        return appDatabase.studyDao()
    }
}
