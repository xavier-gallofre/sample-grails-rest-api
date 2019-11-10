package es.xgani.samplegrailsrestapi

class TaskController {
    static responseFormats = ['json', 'xml']

    def index() {
        respond Task.list()
    }

    def show(Long id) {
        respond Task.get(id)
    }
}
