package org.kurron.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.Task
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.Exec
/**
 * This task will run JAXB's XML schema compiler (xjc).
 */
class XjcTask extends DefaultTask {
    String description = 'Generate JAXB annotated classes from an XML schema file'
    String group = 'Build'
    String outputDirectory = '/tmp'
    String packageName = 'org.kurron.default'
    String schemaFile = '/tmp/does-not-exist.xsd'

    @TaskAction
    def work() {
        println "Current Gradle version:$project.gradle.gradleVersion"
        project.plugins.apply( 'java' )
        Exec task = project.tasks.add( 'bob', Exec.class )
        task.commandLine = ['xjc', '-d', outputDirectory, '-p', packageName, '-xmlschema', '-verbose', '-readOnly', '-mark-generated', schemaFile]
        println( 'name = ' + task.name )
        task.execute()
    }
}
