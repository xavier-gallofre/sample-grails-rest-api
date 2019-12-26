package es.xgani.samplegrailsrestapi.core.dto.mapper

import es.xgani.samplegrailsrestapi.core.Task
import es.xgani.samplegrailsrestapi.core.dto.model.TaskDto
import es.xgani.samplegrailsrestapi.core.request.TaskRequest

class TaskMapper {

    public static TaskDto toDto(Task task) {
        return new TaskDto(
                id: task.id,
                name: task.name,
                description: task.description,
                date: task.date
        )
    }

    static TaskDto toDto(TaskRequest taskRequest) {
        new TaskDto(
                name: taskRequest.name,
                description: taskRequest.description,
                date: taskRequest.dueDate
        )
    }
}
