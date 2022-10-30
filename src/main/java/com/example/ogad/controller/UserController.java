package com.example.ogad.controller;

import com.example.ogad.model.JwtRequest;
import com.example.ogad.model.JwtResponse;
import com.example.ogad.model.User;
import com.example.ogad.model.UserRole;
import com.example.ogad.repository.UserRepository;
import com.example.ogad.repository.UserRoleRepository;
import com.example.ogad.securingweb.JwtTokenUtil;
import com.example.ogad.services.impl.JwtUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;

    @GetMapping
    public ResponseEntity<User> getUser(long id) {
        User user = userRepository.findByUserId(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("asd")
    public ResponseEntity<UserRole> getUsers(long id) {
        //User user = userRepository.findByUserId(id);
        UserRole userRole = userRoleRepository.findByUserRoleId(id);
        return new ResponseEntity<UserRole>(userRole, HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String firstPage() {
        return "Hello World";
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUserName(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUserName());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
