package spring

import es.xgani.samplegrailsrestapi.core.exception.AppExceptionHandler
import es.xgani.security.JwtAuthorizationFilter

// Place your Spring DSL code here
beans = {

    exceptionHandler(AppExceptionHandler) {
        exceptionMappings = ['java.lang.Exception': '/error']
    }

    jwtAuthorizationFilter(JwtAuthorizationFilter) {
        jwtConfiguration = ref('jwtConfiguration')
    }
}
