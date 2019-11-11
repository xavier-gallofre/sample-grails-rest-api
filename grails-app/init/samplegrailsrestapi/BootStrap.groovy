package samplegrailsrestapi

import es.xgani.samplegrailsrestapi.core.Task

class BootStrap {

    def init = { servletContext ->
        new Task(
                name: 'Buy the milk',
                description: 'The classic \'Remember the milk\' task',
                date: new Date() + 1
        ).save()
    }
    def destroy = {
    }
}
