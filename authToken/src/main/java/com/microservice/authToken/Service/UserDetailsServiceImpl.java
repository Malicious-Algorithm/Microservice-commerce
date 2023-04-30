package com.microservice.authToken.Service;

import com.microservice.authToken.Dto.Cliente;
import com.microservice.authToken.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente user = userRepository.findByNombre(username); //TODO: quizas aca hacer la llamada al microservicio de cliente?
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return UserDetailsImpl.build(user);
    }
}