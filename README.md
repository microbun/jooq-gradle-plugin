# jooq-gradle-plugin

# Defining your build.gradle
```
group 'com.oskip'
version '1.0-SNAPSHOT'

apply plugin: 'java'
apply plugin: "com.oskip.jooq"
apply plugin: 'idea'

repositories {
    mavenLocal()
    mavenCentral()
}

buildscript {

    repositories {
        mavenLocal()
        mavenCentral()
    }

    dependencies {
        classpath "com.oskip:jooq:0.1"
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
}

idea {
    module {
        sourceDirs += file("$jooq.generatedSrc");
    }
}

```