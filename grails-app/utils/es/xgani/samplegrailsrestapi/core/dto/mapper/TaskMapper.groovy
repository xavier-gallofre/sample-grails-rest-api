package es.xgani.samplegrailsrestapi.core.dto.mapper

import es.xgani.samplegrailsrestapi.core.Task
import es.xgani.samplegrailsrestapi.core.dto.model.TaskDto

class TaskMapper {

    public static TaskDto toDto(Task task) {
        return new TaskDto(
                id: task.id,
                name: task.name,
                description: task.description,
                date: task.date
        )
    }
}
