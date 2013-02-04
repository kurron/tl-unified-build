package org.kurron.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * An example of creating a configurable task.
 */
class GreetingTask extends DefaultTask {
    String greeting = 'default greeting message'

    @TaskAction
    def greet() {
        println greeting
    }
}
