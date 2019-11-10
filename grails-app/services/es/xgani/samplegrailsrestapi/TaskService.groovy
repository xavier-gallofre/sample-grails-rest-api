package es.xgani.samplegrailsrestapi

import es.xgani.samplegrailsrestapi.dto.mapper.TaskMapper
import es.xgani.samplegrailsrestapi.dto.model.TaskDto
import grails.gorm.transactions.Transactional

@Transactional
class TaskService {

    List<TaskDto> findAll() {
        Task.findAll().collect(TaskMapper.&toDto)
    }

    TaskDto findById(Long id) {
        Task.findById(id).with(TaskMapper.&toDto)
    }
}
