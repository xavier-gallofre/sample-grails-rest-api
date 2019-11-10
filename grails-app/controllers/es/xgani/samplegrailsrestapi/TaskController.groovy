package es.xgani.samplegrailsrestapi

import es.xgani.samplegrailsrestapi.dto.model.TaskDto

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
}
