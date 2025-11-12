package com.example.studyhelper.data.local.repository;

import com.example.studyhelper.data.local.dao.DisciplinaDao;
import com.example.studyhelper.data.local.dao.TarefaDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class StudyRepository_Factory implements Factory<StudyRepository> {
  private final Provider<DisciplinaDao> disciplinaDaoProvider;

  private final Provider<TarefaDao> tarefaDaoProvider;

  private StudyRepository_Factory(Provider<DisciplinaDao> disciplinaDaoProvider,
      Provider<TarefaDao> tarefaDaoProvider) {
    this.disciplinaDaoProvider = disciplinaDaoProvider;
    this.tarefaDaoProvider = tarefaDaoProvider;
  }

  @Override
  public StudyRepository get() {
    return newInstance(disciplinaDaoProvider.get(), tarefaDaoProvider.get());
  }

  public static StudyRepository_Factory create(Provider<DisciplinaDao> disciplinaDaoProvider,
      Provider<TarefaDao> tarefaDaoProvider) {
    return new StudyRepository_Factory(disciplinaDaoProvider, tarefaDaoProvider);
  }

  public static StudyRepository newInstance(DisciplinaDao disciplinaDao, TarefaDao tarefaDao) {
    return new StudyRepository(disciplinaDao, tarefaDao);
  }
}
