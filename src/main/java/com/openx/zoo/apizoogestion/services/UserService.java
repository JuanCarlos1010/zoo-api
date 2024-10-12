package com.openx.zoo.apizoogestion.services;

import com.openx.zoo.apizoogestion.exceptions.BadRequestException;
import com.openx.zoo.apizoogestion.exceptions.InternalServerException;
import com.openx.zoo.apizoogestion.exceptions.NotFoundExeption;
import com.openx.zoo.apizoogestion.models.*;
import com.openx.zoo.apizoogestion.repositories.RoleRepository;
import com.openx.zoo.apizoogestion.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> findAllUsers(){
        try {
            return userRepository.findAll();
        } catch (Exception e){
            throw new InternalServerException(e);
        }
    }

    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundExeption("Usuario no encontrado con el id: " + id));
    }

    @Transactional
    public User createUser(User user){
        Role role = user.getRole();
        if (role == null) {
            throw new NotFoundExeption("El campo rol es obligatorio");
        } else {
            Optional<Role> roleOpt = roleRepository.findById(user.getId());
            if (roleOpt.isEmpty()) {
                throw new NotFoundExeption("Rol no encontrado con id: " + role.getId());
            }
            Optional<User> userOpt = userRepository.findByUsername(user.getUsername());
            if (userOpt.isPresent()) {
                throw new BadRequestException("Usuario con el nombre " + user.getUsername() + " ya existe.");
            }
            user.setRole(roleOpt.get());
            return userRepository.save(user);
        }
    }

    @Transactional
    public User updateUser(User updateUser) {
        Role role = updateUser.getRole();
        if (role == null) {
            throw new BadRequestException("El campo rol es obligatorio");
        }
        return userRepository.findById(updateUser.getId())
                .map(user -> {
                    Optional<Role> roleOpt = roleRepository.findById(role.getId());
                    if (roleOpt.isEmpty()) {
                        throw new NotFoundExeption("Rol no encontrada con id: " + role.getId());
                    }
                    user.setUsername(updateUser.getUsername());
                    user.setPassword(updateUser.getPassword());
                    user.setPhone(updateUser.getPhone());
                    user.setDocumentNumber(updateUser.getDocumentNumber());
                    user.setAddress(updateUser.getAddress());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new NotFoundExeption("Usuario no encontrado con el id: " + updateUser.getId()));
    }

    @Transactional
    public boolean deleteUser(Long id){
        User user = getUserById(id);
        userRepository.delete(user);
        return true;
    }
}
