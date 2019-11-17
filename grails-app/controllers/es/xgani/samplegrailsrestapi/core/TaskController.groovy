package es.xgani.samplegrailsrestapi.core

import es.xgani.samplegrailsrestapi.core.dto.mapper.TaskMapper
import es.xgani.samplegrailsrestapi.core.dto.model.TaskDto
import es.xgani.samplegrailsrestapi.core.request.TaskRequest

class TaskController {

    TaskService taskService

    static responseFormats = ['json', 'xml']

    def index() {
        List<TaskDto> tasks = taskService.findAll()
        respond tasks
    }

    def show(Long id) {
        TaskDto task = taskService.findById(id)
        respond task
    }

    def save(TaskRequest taskRequest) {
        TaskDto task = taskService.create(TaskMapper.toDto(taskRequest))
        respond task
    }

    def delete(Long id) {
        taskService.delete(id)
        render [:]
    }
}
