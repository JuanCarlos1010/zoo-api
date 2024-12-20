package com.openx.zoo.api.security;

import com.nimbusds.jose.JOSEException;
import com.openx.zoo.api.dto.SignInRequest;
import com.openx.zoo.api.dto.UserRequest;
import com.openx.zoo.api.dto.UserResponse;
import com.openx.zoo.api.entity.Permission;
import com.openx.zoo.api.entity.Role;
import com.openx.zoo.api.entity.User;
import com.openx.zoo.api.exception.BadRequestException;
import com.openx.zoo.api.exception.InternalServerException;
import com.openx.zoo.api.exception.NotFoundException;
import com.openx.zoo.api.exception.UnauthorizedException;
import com.openx.zoo.api.repository.RolePermissionRepository;
import com.openx.zoo.api.repository.RoleRepository;
import com.openx.zoo.api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final SecurityService securityService;
    private final RoleRepository roleRepository;
    private final RolePermissionRepository permissionRepository;

    public AuthenticationService(UserRepository userRepository, SecurityService securityService, RoleRepository roleRepository, RolePermissionRepository permissionRepository) {
        this.userRepository = userRepository;
        this.securityService = securityService;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    public UserResponse signIn(SignInRequest request) {
        try {
            User user = userRepository.findByUsername(request.username())
                    .orElseThrow(() -> new UnauthorizedException("Usuario o contraseña incorrectos"));

            String encodedPassword = user.getPassword();
            if(!securityService.matchPasswords(request.password(), encodedPassword)) {
                throw new UnauthorizedException("Contraseña incorrecta");
            }

            List<String> permissions = permissionRepository.findPermissionsByRole(user.getRole())
                    .stream().parallel()
                    .map(Permission::getSystemName)
                    .toList();

            String token = securityService.signToken(user, permissions);
            return UserResponse.builder()
                    .token(token)
                    .userId(user.getId())
                    .username(user.getUsername())
                    .fullName(user.getFullName())
                    .roleName(user.getRole().getName())
                    .build();
        } catch (NotFoundException | UnauthorizedException e) {
            throw e;
        } catch (JOSEException e) {
            throw new InternalServerException(e);
        }
    }

    @Transactional
    public User signUp(UserRequest request) {
        try {
            if(request.getPassword() == null)
                throw new BadRequestException("La contraseña es incorrecta");

            if(!securityService.validPassword(request.getPassword())) {
                throw new BadRequestException("La contraseña no debe contener caracteres especiales");
            }

            if(request.getFullName() == null || request.getFullName().length() < 3)
                throw new BadRequestException("Nombre de usuario incorrecto");

            Role role = roleRepository.findById(request.getRoleId())
                    .orElseThrow(() -> new NotFoundException("Rol no encontrado con id: " + request.getRoleId()));

            Optional<User> userOpt = userRepository.findByUsername(request.getUsername());
            if (userOpt.isPresent()) {
                throw new BadRequestException("El usuario " + request.getUsername() + " ya existe.");
            }

            User user = User.builder()
                    .role(role)
                    .phone(request.getPhone())
                    .address(request.getAddress())
                    .createdAt(LocalDateTime.now())
                    .username(request.getUsername())
                    .fullName(request.getFullName())
                    .documentNumber(request.getDocumentNumber())
                    .password(securityService.encryptPassword(request.getPassword()))
                    .build();

            return userRepository.save(user);
        } catch (BadRequestException | NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerException(e);
        }
    }

    @Transactional
    public User updateAccount(boolean updatePassword, UserRequest body) {
        try {
            if(body.getFullName() == null || body.getFullName().length() < 3)
                throw new BadRequestException("Nombre de usuario incorrecto");

            Role roleRequest = roleRepository.findById(body.getRoleId())
                    .orElseThrow(() -> new NotFoundException("Rol no encontrado con id: " + body.getRoleId()))  ;
;
            if (roleRequest == null) {
                throw new BadRequestException("El campo rol es obligatorio");
            }
            return userRepository.findById(body.getId())
                    .map(user -> {
                        Role role = roleRepository.findById(roleRequest.getId())
                                .orElseThrow(() -> new NotFoundException("Rol no encontrado con id: " + roleRequest.getId()));
                        if(updatePassword) {
                            if(!securityService.validPassword(body.getPassword())) {
                                throw new BadRequestException("Contraseña no debe contener caracteres especiales");
                            }
                            user.setPassword(securityService.encryptPassword(body.getPassword()));
                        }
                        user.setRole(role);
                        user.setPhone(body.getPhone());
                        user.setAddress(body.getAddress());
                        user.setUsername(body.getUsername());
                        user.setFullName(body.getFullName());
                        user.setUpdatedAt(LocalDateTime.now());
                        user.setDocumentNumber(body.getDocumentNumber());
                        return userRepository.save(user);
                    })
                    .orElseThrow(() -> new NotFoundException("Usuario no encontrado con el id: " + body.getId()));
        } catch (BadRequestException | NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerException(e);
        }
    }

    @Transactional
    public boolean deleteAccount(long accountId) {
        try {
            userRepository.findById(accountId)
                   .map(user -> {
                       user.setDeletedAt(LocalDateTime.now());
                       return userRepository.save(user);
                    })
                   .orElseThrow(() -> new NotFoundException("Usuario no encontrado con el id: " + accountId));
            return true;
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerException(e);
        }
    }
}
