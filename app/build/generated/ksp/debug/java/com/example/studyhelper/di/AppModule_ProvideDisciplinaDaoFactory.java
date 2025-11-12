package com.example.studyhelper.di;

import com.example.studyhelper.data.local.StudyDatabase;
import com.example.studyhelper.data.local.dao.DisciplinaDao;
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
public final class AppModule_ProvideDisciplinaDaoFactory implements Factory<DisciplinaDao> {
  private final Provider<StudyDatabase> databaseProvider;

  private AppModule_ProvideDisciplinaDaoFactory(Provider<StudyDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public DisciplinaDao get() {
    return provideDisciplinaDao(databaseProvider.get());
  }

  public static AppModule_ProvideDisciplinaDaoFactory create(
      Provider<StudyDatabase> databaseProvider) {
    return new AppModule_ProvideDisciplinaDaoFactory(databaseProvider);
  }

  public static DisciplinaDao provideDisciplinaDao(StudyDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideDisciplinaDao(database));
  }
}
