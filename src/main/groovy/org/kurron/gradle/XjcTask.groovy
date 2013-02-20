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
    String bindingFile = null

    @TaskAction
    def work() {
        sourceDirectory = applyDefaultSourceDirectory()
        destinationDirectory = applyDefaultDestinationDirectory()
        String sourceFile = createPathToSingleFileOrEntireDirectory(sourceDirectory)
        logger.quiet "Compiling $sourceFile into $destinationDirectory"
        logger.quiet "Generating classes into $packageName package"
        new File( destinationDirectory ).mkdirs()
        String internalName = name.capitalize() + "CommandLine"
        logger.quiet "Adding internal task named $internalName"
        Exec task = project.tasks.add( internalName, Exec.class )
        task.commandLine = ['xjc', '-d', destinationDirectory, '-p', packageName, '-xmlschema', '-verbose', '-readOnly', '-mark-generated', bindingFileCommand(), sourceFile]
        def command = task.commandLine.join( ' ' )
        logger.quiet "Executing $command"
        task.execute()
        project.sourceSets['main'].java.srcDir( destinationDirectory )
    }

    private String bindingFileCommand() {
        String command
        if ( null == bindingFile )
        {
            command = ''
        }
        else
        {
            command = "-npa -b $bindingFile"
        }
        return command
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
