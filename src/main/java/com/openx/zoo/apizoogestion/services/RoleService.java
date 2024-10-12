package com.openx.zoo.apizoogestion.services;

import com.openx.zoo.apizoogestion.exceptions.InternalServerException;
import com.openx.zoo.apizoogestion.exceptions.NotFoundExeption;
import com.openx.zoo.apizoogestion.models.Role;
import com.openx.zoo.apizoogestion.repositories.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAllRoles(){
        try {
            return roleRepository.findAll();
        } catch (Exception e){
            throw new InternalServerException(e);
        }
    }

    public Role getRoleById(Long id){
        return roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundExeption("Rol no encontrado con el id: " + id));
    }

    @Transactional
    public Role createRole(Role role){
        Optional<Role> roleOpt = roleRepository.findByDescription(role.getDescription());
        if (roleOpt.isPresent()){
            throw new NotFoundExeption("Rol con la descripcion " + role.getDescription() + "ya existe.");
        }
        return roleRepository.save(role);
    }

    @Transactional
    public Role updateRole(Role updateRole){
        return roleRepository.findById(updateRole.getId())
                .map(role -> {
                    role.setDescription(role.getDescription());
                    return roleRepository.save(role);
                })
                .orElseThrow(() -> new NotFoundExeption("Rol no encontrado con el id: " + updateRole.getId()));
    }

    @Transactional
    public boolean deleteRole(Long id){
        Role role = getRoleById(id);
        roleRepository.delete(role);
        return true;
    }
}
