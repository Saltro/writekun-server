package com.writekun.server.controller;

import com.writekun.server.exception.UserException;
import com.writekun.server.exception.UsernameAlreadyExistsException;
import com.writekun.server.services.UserService;
import com.writekun.server.utils.JSONReturnBody;
import com.writekun.server.utils.ReturnStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public JSONReturnBody<Object> registerAccount(@RequestBody Map<String, Object> params) {
        String username = (String) params.get("username");
        String password = (String) params.get("password");
        String email = (String) params.get("email");
        try {
            int userId = userService.insertCommonUser(username, password, email);
            Map<String, Object> data = new HashMap<>();
            data.put("userId", userId);
            return new JSONReturnBody<>(ReturnStatus.RESOURCE_CHANGED, data);
        } catch (UsernameAlreadyExistsException e) {
            return new JSONReturnBody<>(ReturnStatus.USERNAME_ALREADY_EXISTS);
        } catch (UserException e) {
            return new JSONReturnBody<>(ReturnStatus.USER_ERROR);
        } catch (Exception e) {
            System.out.println(e);
            return new JSONReturnBody<>(ReturnStatus.UNKNOWN_ERROR);
        }
    }
}
