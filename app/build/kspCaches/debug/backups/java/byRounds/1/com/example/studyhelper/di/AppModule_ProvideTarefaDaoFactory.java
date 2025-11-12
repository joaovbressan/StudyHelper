package com.example.studyhelper.di;

import com.example.studyhelper.data.local.StudyDatabase;
import com.example.studyhelper.data.local.dao.TarefaDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class AppModule_ProvideTarefaDaoFactory implements Factory<TarefaDao> {
  private final Provider<StudyDatabase> databaseProvider;

  private AppModule_ProvideTarefaDaoFactory(Provider<StudyDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public TarefaDao get() {
    return provideTarefaDao(databaseProvider.get());
  }

  public static AppModule_ProvideTarefaDaoFactory create(Provider<StudyDatabase> databaseProvider) {
    return new AppModule_ProvideTarefaDaoFactory(databaseProvider);
  }

  public static TarefaDao provideTarefaDao(StudyDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideTarefaDao(database));
  }
}
