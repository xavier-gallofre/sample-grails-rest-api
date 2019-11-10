package es.xgani.samplegrailsrestapi.dto.mapper

import es.xgani.samplegrailsrestapi.Task
import es.xgani.samplegrailsrestapi.dto.model.TaskDto

class TaskMapper {

    public static TaskDto toDto(Task task) {
        return new TaskDto(
                name: task.name,
                description: task.description,
                date: task.date
        )
    }
}
