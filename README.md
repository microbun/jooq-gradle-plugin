# jooq-gradle-plugin

# Defining your build.gradle
```
group 'com.oskip'
version '1.0-SNAPSHOT'

apply plugin: 'java'
apply plugin: 'idea'
apply plugin: "com.oskip.jooq"

repositories {
    mavenCentral()
}

buildscript {

    repositories {
        maven {
            url "https://plugins.gradle.org/m2/"
        }
    }

    dependencies {
        classpath "gradle.plugin.com.oskip:jooq-gradle-plugin:0.3.2"
        classpath 'org.jooq:jooq-codegen:3.8.5'
        classpath 'org.postgresql:postgresql:9.4-1201-jdbc41'
    }
}

dependencies {
    testCompile 'org.testng:testng:6.9.6'
    compile 'org.jooq:jooq:3.8.5'
}

jooq {
    isGenerate = true
    config = "$project.projectDir/config/generate-config.xml"
}

idea {
    module {
        sourceDirs += file("$jooq.generatedSrc");
    }
}


```
