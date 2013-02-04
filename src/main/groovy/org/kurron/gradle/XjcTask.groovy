package org.kurron.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.Exec
/**
 * This task will run JAXB's XML schema compiler (xjc).
 */
class XjcTask extends DefaultTask {
    String description = 'Generate JAXB annotated classes from an XML schema file'
    String group = 'Build'
    String sourceDirectory = null
    String destinationDirectory = null
    String packageName = 'org.kurron.generated'
    String schemaFile = null

    /*
     inputs.dir schemaDirectory
     outputs.dir destination
     */

    @TaskAction
    def work() {
        if ( null == sourceDirectory )
        {
            sourceDirectory = project.sourceSets['main'].resources.srcDirs.toList().first().path
        }
        if ( null == destinationDirectory )
        {
            destinationDirectory = project.buildDir.path + '/generated/java'
        }
        final String sourceFile
        if ( null == schemaFile )
        {
            sourceFile = sourceDirectory
        }
        else
        {
            sourceFile = sourceDirectory + System.getProperty( 'file.separator' ) + schemaFile
        }
        logger.quiet "Compiling $sourceFile into $destinationDirectory"
        new File( destinationDirectory ).mkdirs()
        Exec task = project.tasks.add( 'bob', Exec.class )
        task.commandLine = ['xjc', '-d', destinationDirectory, '-p', packageName, '-xmlschema', '-verbose', '-readOnly', '-mark-generated', sourceFile]
        task.execute()
        project.sourceSets['main'].java.srcDir( destinationDirectory )
    }
}
