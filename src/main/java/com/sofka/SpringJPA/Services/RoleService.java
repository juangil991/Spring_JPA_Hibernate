package com.sofka.SpringJPA.Services;

import com.sofka.SpringJPA.models.Role;
import com.sofka.SpringJPA.repository.IRoleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Role> getRoleById(long id) {
        Optional<Role> roleData = repoRole.findById(id);

        if (roleData.isPresent()) {
            return new ResponseEntity<>(roleData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Role> createRole(Role role) {
        try {
            Role _role = repoRole.save(new Role(role.getName()));
            return new ResponseEntity<>(_role, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    public ResponseEntity<Role> updateRole( long id, Role role) {
        ResponseEntity<Role> roleData =getRoleById(id);
        if(roleData.hasBody()) {
            roleData.getBody().setName(role.getName());

            repoRole.save(roleData.getBody());
        }
        return roleData;
    }

    public ResponseEntity<String> deleteRole(long id) {
        try {
            repoRole.deleteById(id);
            return new ResponseEntity<>("REPO DELETE!! ",HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    public ResponseEntity<HttpStatus> deleteAllRoles() {
        try {
            repoRole.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

}
