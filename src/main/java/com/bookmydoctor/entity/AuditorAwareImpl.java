package com.bookmydoctor.entity;



import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            System.out.println("Authentication object is null");
            return Optional.of("Anonymous"); 
        }

        if (!authentication.isAuthenticated()) {
            System.out.println("User is not authenticated");
            return Optional.of("Anonymous"); 
        }

        System.out.println("Authenticated user: " + authentication.getName());
        return Optional.of(authentication.getName());
    }
}
