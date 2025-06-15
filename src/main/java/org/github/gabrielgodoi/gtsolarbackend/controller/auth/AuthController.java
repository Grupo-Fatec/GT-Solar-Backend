package org.github.gabrielgodoi.gtsolarbackend.controller.auth;

import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.dto.admin.auth.AuthDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.admin.auth.AuthResponseDTo;
import org.github.gabrielgodoi.gtsolarbackend.entities.admins.Admin;
import org.github.gabrielgodoi.gtsolarbackend.errors.EntityNotFoundException;
import org.github.gabrielgodoi.gtsolarbackend.repositories.AdminRepository;
import org.github.gabrielgodoi.gtsolarbackend.services.auth.AdminAuthService;
import org.github.gabrielgodoi.gtsolarbackend.services.auth.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AdminRepository adminRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final AdminAuthService adminAuthService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTo> login(@RequestBody AuthDto authDto){
        UsernamePasswordAuthenticationToken userDetails = new UsernamePasswordAuthenticationToken(authDto.email(), authDto.password());
        var auth = this.authenticationManager.authenticate(userDetails);
        String token = this.tokenService.generateToken((Admin) auth.getPrincipal());
        this.adminAuthService.loadUserByUsername(authDto.email());
        Admin admin = this.adminRepository.findUserByEmail(authDto.email()).orElseThrow(
                () -> new EntityNotFoundException("user with email: " + authDto.email() + " was not founded")
        );
        return ResponseEntity.ok().body(new AuthResponseDTo(admin.getName(), admin.getEmail(), admin.getAdminRole().getValue(), token));
    }

    //forget password
}
