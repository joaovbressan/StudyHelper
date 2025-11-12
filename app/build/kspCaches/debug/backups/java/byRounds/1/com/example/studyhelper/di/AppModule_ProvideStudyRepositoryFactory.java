package com.example.studyhelper.di;

import com.example.studyhelper.data.local.dao.DisciplinaDao;
import com.example.studyhelper.data.local.dao.TarefaDao;
import com.example.studyhelper.data.local.repository.StudyRepository;
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
public final class AppModule_ProvideStudyRepositoryFactory implements Factory<StudyRepository> {
  private final Provider<DisciplinaDao> disciplinaDaoProvider;

  private final Provider<TarefaDao> tarefaDaoProvider;

  private AppModule_ProvideStudyRepositoryFactory(Provider<DisciplinaDao> disciplinaDaoProvider,
      Provider<TarefaDao> tarefaDaoProvider) {
    this.disciplinaDaoProvider = disciplinaDaoProvider;
    this.tarefaDaoProvider = tarefaDaoProvider;
  }

  @Override
  public StudyRepository get() {
    return provideStudyRepository(disciplinaDaoProvider.get(), tarefaDaoProvider.get());
  }

  public static AppModule_ProvideStudyRepositoryFactory create(
      Provider<DisciplinaDao> disciplinaDaoProvider, Provider<TarefaDao> tarefaDaoProvider) {
    return new AppModule_ProvideStudyRepositoryFactory(disciplinaDaoProvider, tarefaDaoProvider);
  }

  public static StudyRepository provideStudyRepository(DisciplinaDao disciplinaDao,
      TarefaDao tarefaDao) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideStudyRepository(disciplinaDao, tarefaDao));
  }
}
