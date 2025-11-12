package com.example.studyhelper.di

import android.content.Context
import androidx.room.Room
import com.example.studyhelper.data.local.StudyDatabase
import com.example.studyhelper.data.local.dao.DisciplinaDao
import com.example.studyhelper.data.local.dao.TarefaDao
import com.example.studyhelper.data.local.repository.StudyRepository
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
    fun provideStudyDatabase(@ApplicationContext context: Context): StudyDatabase {
        return Room.databaseBuilder(
            context,
            StudyDatabase::class.java,
            "study_helper_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDisciplinaDao(database: StudyDatabase): DisciplinaDao {
        return database.disciplinaDao()
    }

    @Provides
    @Singleton
    fun provideTarefaDao(database: StudyDatabase): TarefaDao {
        return database.tarefaDao()
    }

    @Provides
    @Singleton
    fun provideStudyRepository(
        disciplinaDao: DisciplinaDao,
        tarefaDao: TarefaDao
    ): StudyRepository {
        return StudyRepository(disciplinaDao, tarefaDao)
    }
}