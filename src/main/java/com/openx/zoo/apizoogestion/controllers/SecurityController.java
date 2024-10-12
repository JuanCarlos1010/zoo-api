package com.openx.zoo.apizoogestion.controllers;

import com.openx.zoo.apizoogestion.models.Permission;
import com.openx.zoo.apizoogestion.models.Role;
import com.openx.zoo.apizoogestion.models.RolePermission;
import com.openx.zoo.apizoogestion.services.PermissionService;
import com.openx.zoo.apizoogestion.utility.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/security")
public class SecurityController {
    private final PermissionService permissionService;

    public SecurityController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @RequestMapping("/permissions")
    public ResponseEntity<ApiResponse<Iterable<Permission>>> findAllPermissions() {
        Iterable<Permission> permissions = permissionService.findAllPermissions();
        ApiResponse<Iterable<Permission>> apiResponse = new ApiResponse<>(permissions);
        return ResponseEntity.ok(apiResponse);
    }

    @RequestMapping("/permissions/{id}")
    public ResponseEntity<ApiResponse<Permission>> findPermissionById(@PathVariable(name = "id") long permissionId) {
        Permission permission = permissionService.findPermissionById(permissionId);
        ApiResponse<Permission> apiResponse = new ApiResponse<>(permission);
        return ResponseEntity.ok(apiResponse);
    }

    @RequestMapping("/permissions-role")
    public ResponseEntity<ApiResponse<Iterable<Permission>>> findPermissionsByRole(@RequestParam(name = "role") long roleId) {
        Iterable<Permission> permissions = permissionService.findPermissionsByRole(roleId);
        ApiResponse<Iterable<Permission>> apiResponse = new ApiResponse<>(permissions);
        return ResponseEntity.ok(apiResponse);
    }

    @RequestMapping("/roles/{id}")
    public ResponseEntity<ApiResponse<Role>> findRoleById(@PathVariable(name = "id") long roleId) {
        Role role = permissionService.findRoleById(roleId);
        ApiResponse<Role> apiResponse = new ApiResponse<>(role);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/roles")
    public ResponseEntity<ApiResponse<Role>> createRole(@RequestBody Role body) {
        Role role = permissionService.createRole(body);
        ApiResponse<Role> apiResponse = new ApiResponse<>(role);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/roles")
    public ResponseEntity<ApiResponse<Role>> updateRole(@RequestBody Role body) {
        Role role = permissionService.update(body);
        ApiResponse<Role> apiResponse = new ApiResponse<>(role);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/role-permissions")
    public ResponseEntity<ApiResponse<RolePermission>> createRolePermission(@RequestBody RolePermission body) {
        RolePermission rolePermission = permissionService.createRolePermission(body);
        ApiResponse<RolePermission> apiResponse = new ApiResponse<>(rolePermission);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/role-permissions/{id}")
    public ResponseEntity<ApiResponse<Boolean>> createRolePermission(@PathVariable(name = "id") long id) {
        Boolean rolePermission = permissionService.deleteRolePermission(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(rolePermission);
        return ResponseEntity.ok(apiResponse);
    }
}
