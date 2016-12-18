package com.oskip.jooq

import org.gradle.internal.impldep.org.apache.commons.io.FileUtils

/**
 * @author Microbun on 16/12/18.
 */
class BuildUtil {

    static File prepareTestDir(String projectName, URL pluginClasspathResource) {
        def testProjectDir = BuildUtil.copy2TestDir(projectName)

        // Get build file
        def buildFile = new File(testProjectDir.path, "build.gradle")

        // Add the logic under test to the test build
        def pluginClasspathString = pluginClasspath(pluginClasspathResource)
        buildFile << """
        buildscript {
            dependencies {
                classpath files($pluginClasspathString)
            }
        }
        """
        return testProjectDir
    }

    static File copy2TestDir(String projectName) {
        def baseProjectDir = new File(System.getProperty("user.dir"), projectName)
        def testProjectDir = new File(System.getProperty('user.dir'), 'build/tests/' + projectName)
        FileUtils.deleteDirectory(testProjectDir)
        FileUtils.forceMkdir(testProjectDir)
        FileUtils.copyDirectory(baseProjectDir, testProjectDir)
        return testProjectDir
    }

    static String pluginClasspath(URL pluginClasspathResource) {
        if (pluginClasspathResource == null) {
            throw new IllegalStateException("Did not find plugin classpath resource, run `testClasses` build task.")
        }
        return pluginClasspathResource.readLines()
                .collect { it.replace('\\', '\\\\') } // escape backslashes in Windows paths
                .collect { "'$it'" }
                .join(", ")
    }

}
