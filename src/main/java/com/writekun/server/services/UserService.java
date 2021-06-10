package com.writekun.server.services;

import com.writekun.server.exception.UserException;
import com.writekun.server.exception.UsernameAlreadyExistsException;
import com.writekun.server.mapper.UserRepository;
import com.writekun.server.security.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public int insertCommonUser(String username, String password, String email) throws UsernameAlreadyExistsException, UserException {
        String role = "USER";
        UserEntity user = new UserEntity(0, username, passwordEncoder.encode(password), email, role, 1, new ArrayList<>());
        int insertResult = userRepository.insert(user);
        if (insertResult == 0) {
            throw new UsernameAlreadyExistsException();
        } else if (insertResult != 1) {
            throw new UserException();
        }
        return user.getUserId();
    }
}
