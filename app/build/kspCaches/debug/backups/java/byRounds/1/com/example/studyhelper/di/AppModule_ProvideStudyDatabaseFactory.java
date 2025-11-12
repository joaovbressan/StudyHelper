package com.example.studyhelper.di;

import android.content.Context;
import com.example.studyhelper.data.local.StudyDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class AppModule_ProvideStudyDatabaseFactory implements Factory<StudyDatabase> {
  private final Provider<Context> contextProvider;

  private AppModule_ProvideStudyDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public StudyDatabase get() {
    return provideStudyDatabase(contextProvider.get());
  }

  public static AppModule_ProvideStudyDatabaseFactory create(Provider<Context> contextProvider) {
    return new AppModule_ProvideStudyDatabaseFactory(contextProvider);
  }

  public static StudyDatabase provideStudyDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideStudyDatabase(context));
  }
}
