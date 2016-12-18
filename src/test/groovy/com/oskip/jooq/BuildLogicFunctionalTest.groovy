package com.oskip.jooq

import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import spock.lang.Specification

/**
 * @author Microbun on 16/12/18.
 */
class BuildLogicFunctionalTest extends Specification {

    URL pluginClasspathResource

    def setup() {
        pluginClasspathResource = getClass().classLoader.findResource("plugin-classpath.txt")
    }


    def "test jooq "() {
        given:
        def testProjectDir = BuildUtil.prepareTestDir("testProject", pluginClasspathResource)
        when:
        def result = GradleRunner.create()
                .withProjectDir(testProjectDir)
                .withArguments('build').withDebug(true)
                .build()
        then:
        println(result.output)
        result.task(":generateJooq").outcome == TaskOutcome.SUCCESS
    }

}
