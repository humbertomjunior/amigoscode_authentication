package com.example.demo.authentication;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.appuser.AppUserRole;
import com.example.demo.appuser.AppUserService;
import com.example.demo.authentication.register.EmailValidator;
import com.example.demo.authentication.register.RegisterRequest;
import com.example.demo.email.EmailSender;
import com.example.demo.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AppUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailValidator emailValidator;
    private final AppUserService appUserService;
    private final EmailSender emailSender;

    public AuthenticationResponse register (RegisterRequest registerRequest) {

        if(!emailValidator.test(registerRequest.getEmail())) {
            throw new IllegalStateException("Invalid e-mail");
        }

        if(repository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new IllegalStateException("Email already taken");
        }

        AppUser user = AppUser.builder()
                .firstname(registerRequest.getFirstName())
                .lastname(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .appUserRole(AppUserRole.USER)
                .isEnabled(true)
                .isLocked(false)
                .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        var user = repository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();

    }
}
