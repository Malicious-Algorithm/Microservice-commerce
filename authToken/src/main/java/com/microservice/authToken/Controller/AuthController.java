package com.microservice.authToken.Controller;

import com.microservice.authToken.Dto.Cliente;
import com.microservice.authToken.Service.UserDetailsImpl;
import com.microservice.authToken.jwt.JwtUtil;
import com.microservice.authToken.payload.request.JwtRequest;
import com.microservice.authToken.payload.response.JwtResponse;
import com.microservice.authToken.repository.UserRepository;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static com.microservice.authToken.jwt.JwtUtil.SECRET_KEY;


@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getNombre(), authenticationRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getNombre(), authenticationRequest.getPassword()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getNombre());

        final String jwt = jwtUtil.generateToken(userDetails);
        UserDetailsImpl userDetails1 = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails1.getEmail(), userDetails1.getId(), userDetails1.getUsername()));
    }

    @GetMapping("/getAuthentcated")
    public ResponseEntity<?> getData(@RequestHeader("Authorization") String authHeader) {
        //yeah, now comes the tricky part.
        String token = authHeader.substring("Bearer ".length());
        Claims claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody(); //? this decodes the incoming JWT
        String username = claims.getSubject();
        Cliente user = userRepository.findByNombre(username); //TODO: quizas aca hacer la llamada al microservicio de cliente?
        return ResponseEntity.ok(user);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("Usuario deshabilitado", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Credenciales incorrectas", e);
        }
    }
}