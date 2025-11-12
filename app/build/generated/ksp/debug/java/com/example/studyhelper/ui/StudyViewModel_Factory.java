package com.example.studyhelper.ui;

import com.example.studyhelper.data.local.repository.StudyRepository;
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
public final class StudyViewModel_Factory implements Factory<StudyViewModel> {
  private final Provider<StudyRepository> repositoryProvider;

  private StudyViewModel_Factory(Provider<StudyRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public StudyViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static StudyViewModel_Factory create(Provider<StudyRepository> repositoryProvider) {
    return new StudyViewModel_Factory(repositoryProvider);
  }

  public static StudyViewModel newInstance(StudyRepository repository) {
    return new StudyViewModel(repository);
  }
}
