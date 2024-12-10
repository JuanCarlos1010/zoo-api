package com.openx.zoo.api.service;

import com.openx.zoo.api.exception.InternalServerException;
import com.openx.zoo.api.exception.NotFoundException;
import com.openx.zoo.api.entity.*;
import com.openx.zoo.api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        try {
            return userRepository.findAllByDeletedAtIsNull();
        } catch (Exception e) {
            throw new InternalServerException(e);
        }
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado con el id: " + id));
    }

    @Transactional
    public boolean deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
        return true;
    }
}
