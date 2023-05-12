package br.eti.gadelha.nutrition.security;

import br.eti.gadelha.nutrition.persistence.model.Privilege;
import br.eti.gadelha.nutrition.persistence.model.Role;
import br.eti.gadelha.nutrition.persistence.model.UserEntity;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryPrivilege;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryRole;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryUserEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;
    @Autowired
    private RepositoryUserEntity repositoryUserEntity;
    @Autowired
    private RepositoryRole repositoryRole;
    @Autowired
    private RepositoryPrivilege repositoryPrivilege;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

        Role adminRole = repositoryRole.findByName("ROLE_ADMIN");
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("Test");
        userEntity.setPassword(passwordEncoder.encode("test"));
        userEntity.setEmail("test@test.com");
        userEntity.setRole(Arrays.asList(adminRole));
        userEntity.setActive(true);
        repositoryUserEntity.save(userEntity);
        alreadySetup = true;
    }
    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = repositoryPrivilege.findByName(name);
        if (privilege == null) {
            privilege = new Privilege();
            privilege.setName(name);
            repositoryPrivilege.save(privilege);
        }
        return privilege;
    }
    @Transactional
    Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {
        Role role = repositoryRole.findByName(name);
        if (role == null) {
            role = new Role();
            role.setName(name);
//            role.setPrivileges((Set<Privilege>) privileges);
            repositoryRole.save(role);
        }
        return role;
    }
}