package com.sofka.SpringJPA.Controllers;

import com.sofka.SpringJPA.Services.RoleService;
import com.sofka.SpringJPA.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("/roles")
    public ResponseEntity<List<Role>>allRoles(@RequestParam(required = false) String name){
        return roleService.getAllRoles(name);
    }
    @GetMapping("/roles/query")
    public ResponseEntity<List<Role>>allTutorialsTitle(@RequestParam("name") String name){
        return roleService.getAllRoles(name);
    }


}
