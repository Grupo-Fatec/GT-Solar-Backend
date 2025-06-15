package org.github.gabrielgodoi.gtsolarbackend.services.auth;

import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.repositories.AdminRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminAuthService implements UserDetailsService {
    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.adminRepository.findByEmail(email);
    }
}
