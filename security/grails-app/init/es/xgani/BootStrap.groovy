package es.xgani

import es.xgani.security.Role
import es.xgani.security.User
import es.xgani.security.UserRole

class BootStrap {

    def init = { servletContext ->
        Role role = new Role(authority: 'ROLE_ADMIN').save()
        User user = new User(username: 'admin', password: 'pass').save(flush: true)
        new UserRole(user: user, role: role).save()
    }

    def destroy = {
    }
}
