package com.example.studyhelper;

import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedEntryPoint;

@OriginatingElement(
    topLevelClass = StudyHelperApp.class
)
@GeneratedEntryPoint
@InstallIn(SingletonComponent.class)
public interface StudyHelperApp_GeneratedInjector {
  void injectStudyHelperApp(StudyHelperApp studyHelperApp);
}
