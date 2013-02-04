package org.kurron.gradle

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
