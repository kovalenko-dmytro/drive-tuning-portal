package com.drivetuningsh.service.user.impl;


import com.drivetuningsh.dto.UserRequestDto;
import com.drivetuningsh.entity.user.Role;
import com.drivetuningsh.entity.user.RoleEnum;
import com.drivetuningsh.entity.user.User;
import com.drivetuningsh.repository.user.RoleRepository;
import com.drivetuningsh.repository.user.UserRepository;
import com.drivetuningsh.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public User save(UserRequestDto userRequestDto) {
        Role userRole = roleRepository.findByRole(RoleEnum.USER.getRole());
        User newUser = new User();

        newUser.setFirstName(userRequestDto.getFirstName());
        newUser.setLastName(userRequestDto.getLastName());
        newUser.setEmail(userRequestDto.getEmail());
        newUser.setPassword(encoder.encode(userRequestDto.getPassword()));
        newUser.setEmailVerified(true);
        newUser.setRoles(new HashSet<>(Collections.singletonList(userRole)));

        return userRepository.save(newUser);
    }

    @Override
    public void saveAdmin(String ... args) {
        if (userRepository.findByEmail(args[2]) == null) {
            Role userRole = roleRepository.findByRole(RoleEnum.ADMIN.getRole());
            User admin = new User();

            admin.setFirstName(args[0]);
            admin.setLastName(args[1]);
            admin.setEmail(args[2]);
            admin.setPassword(encoder.encode(args[3]));
            admin.setEmailVerified(true);
            admin.setRoles(new HashSet<>(Collections.singletonList(userRole)));

            userRepository.save(admin);
        }
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
