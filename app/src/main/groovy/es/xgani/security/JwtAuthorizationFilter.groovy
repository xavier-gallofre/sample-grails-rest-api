package es.xgani.security

import es.xgani.config.JwtConfiguration
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import io.jsonwebtoken.*
import io.jsonwebtoken.security.SignatureException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Slf4j
@CompileStatic
class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    JwtConfiguration jwtConfiguration

    JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager)
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request)
        if (authentication == null) {
            filterChain.doFilter(request, response)
            return
        }

        SecurityContextHolder.getContext().setAuthentication(authentication)
        filterChain.doFilter(request, response)
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(jwtConfiguration.header)
        if (token?.startsWith(jwtConfiguration.prefix)) {
            try {
                byte[] signingKey = jwtConfiguration.secret.getBytes()

                Jws<Claims> parsedToken = Jwts.parser()
                        .setSigningKey(signingKey)
                        .parseClaimsJws(token.replace("Bearer ", ""))

                String username = parsedToken
                        .getBody()
                        .getSubject()

                List<SimpleGrantedAuthority> authorities = parsedToken.getBody().get("roles").collect { new SimpleGrantedAuthority(it as String) }

                if (username) {
                    return new UsernamePasswordAuthenticationToken(username, null, authorities)
                }
            } catch (ExpiredJwtException exception) {
                log.warn("Request to parse expired JWT : {} failed : {}", token, exception.getMessage())
            } catch (UnsupportedJwtException exception) {
                log.warn("Request to parse unsupported JWT : {} failed : {}", token, exception.getMessage())
            } catch (MalformedJwtException exception) {
                log.warn("Request to parse invalid JWT : {} failed : {}", token, exception.getMessage())
            } catch (SignatureException exception) {
                log.warn("Request to parse JWT with invalid signature : {} failed : {}", token, exception.getMessage())
            } catch (IllegalArgumentException exception) {
                log.warn("Request to parse empty or null JWT : {} failed : {}", token, exception.getMessage())
            }
        }

        return null
    }
}
