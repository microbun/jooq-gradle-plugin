package com.oskip.jooq

import org.gradle.api.DefaultTask
import org.gradle.api.Task
import org.gradle.api.tasks.TaskAction
import org.jooq.util.GenerationTool
import org.jooq.util.jaxb.Configuration
import org.jooq.util.jaxb.Target

import javax.xml.bind.JAXB

/**
 * @author Microbun on 16/11/12.
 */

class JQGeneratorTask extends DefaultTask {

    Task compileTask

    @TaskAction
    def action() {
        if (project.jooq.isGenerate) {

            if (!project.jooq.config) {
                project.jooq.config = "$project.projectDir.path/config/jooq-codegen.xml"
            }
            def generatedSrc = project.jooq.generatedSrc
            File configurationXml = new File(project.jooq.config)
            Configuration configuration;
            if (configurationXml.exists()) {
                configuration = JAXB.unmarshal(configurationXml, Configuration.class);
                def target = configuration.generator.target
                if (!target) {
                    target = new Target()
                    configuration.generator.withTarget(target)
                }
                target.directory = generatedSrc
                // compile source dir
                File outputDir = new File(generatedSrc)
                compileTask.source outputDir
                GenerationTool.generate(configuration);
            } else {
                logger.error "can not find file:{$configurationXml.path}"
            }
        }
    }

}
