package com.drivetuningsh.service.user;

import com.drivetuningsh.dto.UserRequestDto;
import com.drivetuningsh.entity.user.User;

public interface UserService {
    User findByEmail(String email);
    User save(UserRequestDto userRequestDto);
}
