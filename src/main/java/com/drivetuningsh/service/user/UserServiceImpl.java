package com.drivetuningsh.service.user;


import com.drivetuningsh.dto.UserRequestDto;
import com.drivetuningsh.entity.user.Role;
import com.drivetuningsh.entity.user.RoleEnum;
import com.drivetuningsh.entity.user.User;
import com.drivetuningsh.repository.user.RoleRepository;
import com.drivetuningsh.repository.user.UserRepository;
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
    public void save(UserRequestDto userRequestDto) {
        Role userRole = roleRepository.findByRole(RoleEnum.USER.getRole());
        User newUser = new User();

        newUser.setFirstName(userRequestDto.getFirstName());
        newUser.setLastName(userRequestDto.getLastName());
        newUser.setEmail(userRequestDto.getEmail());
        newUser.setPassword(encoder.encode(userRequestDto.getPassword()));
        newUser.setEmailVerified(true);
        newUser.setRoles(new HashSet<>(Collections.singletonList(userRole)));

        userRepository.save(newUser);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
