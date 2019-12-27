package es.xgani.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = 'grails.config.apps.security')
class JwtConfiguration {

    String loginUrl = '/api/login'
    String secret

    String header = "Authorization"
    String prefix = "Bearer "

    String type = "JWT"
    String issuer = "xgani"
    String audience = "security"

}
