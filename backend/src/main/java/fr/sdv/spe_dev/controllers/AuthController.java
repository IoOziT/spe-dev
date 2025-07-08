package fr.sdv.spe_dev.controllers;

import fr.sdv.spe_dev.auth.JwtUtil;
import fr.sdv.spe_dev.dtos.InputUserDTO;
import fr.sdv.spe_dev.dtos.JwtResponse;
import fr.sdv.spe_dev.dtos.LoginRequestDTO;
import fr.sdv.spe_dev.dtos.OutputUserDTO;
import fr.sdv.spe_dev.services.UserService;
import fr.sdv.spe_dev.validation.groups.OnCreate;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public OutputUserDTO registerUser(@RequestBody @Validated(OnCreate.class) InputUserDTO userDTO) {
        return userService.register(userDTO);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public JwtResponse login(@RequestBody @Validated LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(),
                        loginRequestDTO.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        OutputUserDTO user = userService.getByEmail(loginRequestDTO.getEmail());
        String token = jwtUtil.generateToken(user);

        return new JwtResponse(token, user);
    }
}
