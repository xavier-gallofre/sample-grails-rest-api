package es.xgani.samplegrailsrestapi.core

import es.xgani.samplegrailsrestapi.core.dto.mapper.TaskMapper
import es.xgani.samplegrailsrestapi.core.dto.model.TaskDto
import es.xgani.samplegrailsrestapi.core.repository.TaskRepository
import grails.gorm.transactions.Transactional

@Transactional
class TaskService {

    TaskRepository taskRepository

    List<TaskDto> findAll() {
        taskRepository.findAll().collect(TaskMapper.&toDto)
    }

    TaskDto findById(Long id) {
        taskRepository.findById(id).with(TaskMapper.&toDto)
    }
}
