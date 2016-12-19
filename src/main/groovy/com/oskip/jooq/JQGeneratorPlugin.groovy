package com.oskip.jooq

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.plugins.JavaBasePlugin

/**
 * @author Microbun on 16/11/11.
 */
class JQGeneratorPlugin implements Plugin<Project> {

    private static final String JOOQ_EXTENSION_NAME = "jooq"

    private Project project

    @Override
    void apply(Project project) {
        this.project = project
        project.plugins.apply(JavaBasePlugin.class)
        createExtensions()
        bindTask()
    }

    void createExtensions() {
        project.extensions.create(JOOQ_EXTENSION_NAME, JQGeneratorPluginExtension)
        project.jooq.generatedSrc = "$project.buildDir/generated/source/jooq/main/java"
    }

    void bindTask() {
        JQGeneratorTask generateJooq = project.tasks.create("generateJooq", JQGeneratorTask.class)
        Task javaCompileTask = project.tasks.getByName("compileJava")
        generateJooq.compileTask = javaCompileTask
        javaCompileTask.dependsOn(generateJooq)

    }
}

