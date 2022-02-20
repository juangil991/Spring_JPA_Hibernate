package com.sofka.SpringJPA.Services;

import com.sofka.SpringJPA.models.Role;
import com.sofka.SpringJPA.repository.IRoleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    IRoleJpaRepository repoRole;

    public ResponseEntity<List<Role>> getAllRoles(String name) {
        try {
            List<Role> roles = new ArrayList<Role>();

            if (name == null)
                repoRole.findAll().forEach(roles::add);
            else
                repoRole.findByName(name).forEach(roles::add);

            if (roles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(roles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
