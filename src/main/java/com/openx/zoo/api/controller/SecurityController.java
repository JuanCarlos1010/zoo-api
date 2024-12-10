package com.openx.zoo.api.controller;

import com.openx.zoo.api.dto.RoleDTO;
import com.openx.zoo.api.entity.Permission;
import com.openx.zoo.api.entity.Role;
import com.openx.zoo.api.entity.RolePermission;
import com.openx.zoo.api.mapper.RoleMapper;
import com.openx.zoo.api.service.PermissionService;
import com.openx.zoo.api.utils.ApiResponse;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(path = "/security")
public class SecurityController {
    private final PermissionService permissionService;
    private final RoleMapper roleMapper;

    public SecurityController(PermissionService permissionService, RoleMapper roleMapper) {
        this.permissionService = permissionService;
        this.roleMapper = roleMapper;
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

    @RequestMapping("/permissions-role/{roleId}")
    public ResponseEntity<ApiResponse<Iterable<Permission>>> findPermissionsByRole(@PathVariable(name = "roleId") long roleId) {
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

    @GetMapping("/roles")
    public ResponseEntity<ApiResponse<List<RoleDTO>>> findAllRoles() {
        List<RoleDTO> roles = roleMapper.toDTO(permissionService.findAllRoles());
        ApiResponse<List<RoleDTO>> apiResponse = new ApiResponse<>(roles);
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
        Role role = permissionService.updateRole(body);
        ApiResponse<Role> apiResponse = new ApiResponse<>(role);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/role-permissions")
    public ResponseEntity<ApiResponse<List<String>>> findByUser(@RequestParam(name = "username") String username) {
        List<String> permissions = permissionService.findPermissionsByUser(username);
        ApiResponse<List<String>> apiResponse = new ApiResponse<>(permissions);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/role-permissions")
    public ResponseEntity<ApiResponse<RolePermission>> updateRolePermission(@RequestBody RolePermission body) {
        RolePermission rolePermission = permissionService.createRolePermission(body);
        ApiResponse<RolePermission> apiResponse = new ApiResponse<>(rolePermission);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/role-permissions/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteRolePermission(@PathVariable(name = "id") long id) {
        Boolean rolePermission = permissionService.deleteRolePermission(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(rolePermission);
        return ResponseEntity.ok(apiResponse);
    }
}
