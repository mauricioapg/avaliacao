package com.avaliacao.crud.controller;

import com.avaliacao.crud.dto.LoginRequestDTO;
import com.avaliacao.crud.dto.LoginResponseDTO;
import com.avaliacao.crud.service.UserDetailsService;
import com.avaliacao.crud.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequestDTO loginRequestDTO) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDTO.getUsername());

        if(loginRequestDTO.getUsername().equals(userDetails.getUsername()) &&
                loginRequestDTO.getPassword().equals(userDetails.getPassword())){
            final String token = jwtUtil.generateToken(loginRequestDTO.getUsername());

            return ResponseEntity.ok(new LoginResponseDTO(token));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

}

