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
    //TODO: figure out how to use incremental build support
    String sourceDirectory = null
    String destinationDirectory = null
    String packageName = 'org.kurron.generated'
    String schemaFile = null

    @TaskAction
    def work() {
        sourceDirectory = applyDefaultSourceDirectory()
        destinationDirectory = applyDefaultDestinationDirectory()
        String sourceFile = createPathToSingleFileOrEntireDirectory(sourceDirectory)
        logger.quiet "Compiling $sourceFile into $destinationDirectory"
        logger.quiet "Generating classes into $packageName package"
        new File( destinationDirectory ).mkdirs()
        Exec task = project.tasks.add( 'xjcCommandLine', Exec.class )
        task.commandLine = ['xjc', '-d', destinationDirectory, '-p', packageName, '-xmlschema', '-verbose', '-readOnly', '-mark-generated', sourceFile]
        def command = task.commandLine.join( ' ' )
        logger.quiet "Executing $command"
        task.execute()
        project.sourceSets['main'].java.srcDir( destinationDirectory )
    }

    private createPathToSingleFileOrEntireDirectory(String sourceDirectory) {
        final String sourceFile
        if (null == schemaFile) {
            sourceFile = sourceDirectory
        } else {
            sourceFile = sourceDirectory + System.getProperty('file.separator') + schemaFile
        }
        return sourceFile
    }

    private applyDefaultDestinationDirectory() {
        if (null == destinationDirectory) {
            destinationDirectory = project.buildDir.path + '/generated/java'
        }
        return destinationDirectory
    }

    private applyDefaultSourceDirectory() {
        if (null == sourceDirectory) {
            sourceDirectory = project.sourceSets['main'].resources.srcDirs.toList().first().path
        }
        return sourceDirectory
    }
}
