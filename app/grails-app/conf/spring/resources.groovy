package spring

import es.xgani.samplegrailsrestapi.core.exception.AppExceptionHandler

// Place your Spring DSL code here
beans = {

    exceptionHandler(AppExceptionHandler) {
        exceptionMappings = ['java.lang.Exception': '/error']
    }

}
