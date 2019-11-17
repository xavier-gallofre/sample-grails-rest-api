package es.xgani.samplegrailsrestapi.core.repository

import es.xgani.samplegrailsrestapi.core.Task
import grails.gorm.services.Service

@Service(Task)
interface TaskRepository {

    List<Task> findAll()

    Task findById(Long id)

    Task save(Task task)

    Number delete(Long id)
}
