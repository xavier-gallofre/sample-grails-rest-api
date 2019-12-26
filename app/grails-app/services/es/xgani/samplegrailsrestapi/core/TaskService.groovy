package es.xgani.samplegrailsrestapi.core

import es.xgani.samplegrailsrestapi.core.dto.mapper.TaskMapper
import es.xgani.samplegrailsrestapi.core.dto.model.TaskDto
import es.xgani.samplegrailsrestapi.core.exception.TaskNotFoundException
import es.xgani.samplegrailsrestapi.core.repository.TaskRepository
import grails.gorm.transactions.Transactional

@Transactional
class TaskService {

    TaskRepository taskRepository

    List<TaskDto> findAll() {
        taskRepository.findAll().collect(TaskMapper.&toDto)
    }

    TaskDto findById(Long id) {
        Task task = taskRepository.findById(id)
        if (!task) {
            throw new TaskNotFoundException(id)
        }
        task.with(TaskMapper.&toDto)
    }

    TaskDto create(TaskDto taskDto) {
        Task task = new Task()
        fill(task, taskDto)
        TaskMapper.toDto(taskRepository.save(task))
    }

    void delete(Long id) {
        taskRepository.delete(id)
    }

    TaskDto update(Long id, TaskDto taskDto) {
        Task task = taskRepository.findById(id)
        if (!task) {
            throw new TaskNotFoundException(id)
        }
        fill(task, taskDto)
        task.with(TaskMapper.&toDto)
    }

    protected void fill(Task task, TaskDto taskDto) {
        task.name = taskDto.name
        task.description = taskDto.description
        task.date = taskDto.date
    }
}
