package es.xgani.samplegrailsrestapi


import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class TaskSpec extends Specification implements DomainUnitTest<Task> {

    def setup() {
    }

    def cleanup() {
    }

    void "When a Task is created is necessary to give a name and a date but not a description"() {
        given: "Some empty task"
        Task task = new Task()
        task.validate()

        expect: "to have some especific errors"
        task.hasErrors()
        task.errors.allErrors.size() == 2
        task.errors.getFieldError('name')
        !task.errors.getFieldError('description')
        task.errors.getFieldError('date')
    }
}
