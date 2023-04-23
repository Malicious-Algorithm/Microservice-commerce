package com.microservice.authToken.Service;

import com.microservice.authToken.Dto.Cliente;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@SuppressWarnings("serial")
public class UserDetailsImpl implements UserDetails {

    private Integer id;
    private String nombre;
    private String email;
    private String password;
    private String apellido;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Integer id, String email, String password,String nombre,String apellido) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.apellido=apellido;
    }


    public static UserDetailsImpl build(Cliente usuarioDTO) {

        return new UserDetailsImpl(
                usuarioDTO.getId(),
                usuarioDTO.getEmail(),
                usuarioDTO.getPassword(),
                usuarioDTO.getNombre(),
                usuarioDTO.getApellido()
        );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}

