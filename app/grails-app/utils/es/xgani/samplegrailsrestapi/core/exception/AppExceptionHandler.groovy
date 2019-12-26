package es.xgani.samplegrailsrestapi.core.exception

import org.grails.web.errors.GrailsExceptionResolver
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.ModelAndView

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AppExceptionHandler extends GrailsExceptionResolver {

    @Override
    ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex.cause instanceof TaskNotFoundException) {
            handleTaskNotFoundException(ex.cause as TaskNotFoundException)
        } else {
            return super.resolveException(request, response, handler, ex)
        }
    }

    @ExceptionHandler(TaskNotFoundException.class)
    ModelAndView handleTaskNotFoundException(TaskNotFoundException tnfe) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, 'Entity not found', tnfe)
        return buildResponse(apiError)
    }

    private ModelAndView buildResponse(ApiError apiError) {
        new ModelAndView('/apiError', [
                apiError: apiError
        ], apiError.getStatus())
    }

}
