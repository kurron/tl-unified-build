package org.kurron.gradle

import org.gradle.api.tasks.TaskAction

/**
 * An example of creating a configurable task.
 */
class GreetingTask {
    String greeting = 'default greeting message'

    @TaskAction
    def greet() {
        println greeting
    }
}
