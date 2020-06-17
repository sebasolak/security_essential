package com.example.blank_project.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.blank_project.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE)),
    ADMINTRAINNE(Sets.newHashSet(COURSE_READ, STUDENT_READ));

    private final Set<ApplicationUserPermission> permissionsFromApplicationUserRole;

    ApplicationUserRole(Set<ApplicationUserPermission> permissionsFromApplicationUserRole) {
        this.permissionsFromApplicationUserRole = permissionsFromApplicationUserRole;
    }

    public Set<ApplicationUserPermission> getPermissionsFromApplicationUserRole() {
        return permissionsFromApplicationUserRole;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthoritiesFromUserRole() {

        Set<SimpleGrantedAuthority> thePermission = getPermissionsFromApplicationUserRole().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        thePermission.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return thePermission;
    }
}
