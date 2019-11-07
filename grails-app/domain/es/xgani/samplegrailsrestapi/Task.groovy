package es.xgani.samplegrailsrestapi

class Task {

    String name
    String description
    Date date

    static constraints = {
        name nullable: false, blank: false
        description nullable: true, blank: false
        date nullable: false
    }

}
