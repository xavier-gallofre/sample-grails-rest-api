package samplegrailsrestapi

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import groovy.transform.CompileStatic
import org.springframework.context.annotation.ComponentScan

@ComponentScan('es.xgani.config')
@CompileStatic
class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }
}