package com.example.spring2023.extern.Tests;


import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.junit.AnalyzeClasses;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;


@AnalyzeClasses(packages = "com.example")
public class ArchUnitTests {
    static String PACKAGE_NAME="com.example.spring2023.";
    @ArchTest
    static final ArchRule testLayeredArch=layeredArchitecture().consideringAllDependencies()
                .layer("Domain").definedBy(PACKAGE_NAME+"domain..")
                .layer("App").definedBy(PACKAGE_NAME+"app..")
                .layer("Extern").definedBy(PACKAGE_NAME+"extern..")

                .whereLayer("App").mayOnlyBeAccessedByLayers("App","Extern")
                .whereLayer("Extern").mayOnlyBeAccessedByLayers("Extern");
    }

