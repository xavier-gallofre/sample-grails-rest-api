package es.xgani.samplegrailsrestapi.core.exception

class TaskNotFoundException extends RuntimeException {

    TaskNotFoundException(Long id) {
        super("Could not find task $id")
    }
}
