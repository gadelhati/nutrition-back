package br.eti.gadelha.nutrition.service;

import br.eti.gadelha.nutrition.persistence.model.Role;
import br.eti.gadelha.nutrition.persistence.model.UserEntity;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class ServiceCustomUserDetails implements UserDetailsService {

    private final RepositoryUserEntity repositoryUserEntity;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repositoryUserEntity.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return new User(user.getUsername(), user.getPassword(), mapRolesToAuthorities((List<Role>) user.getRoles()));
    }
    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}