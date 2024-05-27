package com.web.api;

import com.web.dto.*;
import com.web.dto.request.FilterUserRequest;
import com.web.entity.User;
import com.web.enums.ActiveStatus;
import com.web.jwt.JwtTokenProvider;
import com.web.repository.UserRepository;
import com.web.exception.MessageException;
import com.web.service.UserService;
import com.web.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.sql.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserApi {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginDto loginDto) {
        TokenDto tokenDto = userService.login(loginDto);
        return new ResponseEntity<>(tokenDto, HttpStatus.OK);
    }

    @PostMapping("/regis")
    public ResponseEntity<?> regisUser(@RequestBody User user) {
        User result = userService.regis(user);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/admin/lock-user")
    public ResponseEntity<?> lock(@RequestParam(value = "id") Long id){
        String mess = userService.lockOrUnlock(id);
        return new ResponseEntity<>(mess, HttpStatus.OK);
    }

    @PostMapping("/update-infor")
    public ResponseEntity<?> updateInfor(@RequestBody User user){
        User result = userService.updateInfor(user);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/admin/create-update")
    public ResponseEntity<?> saveAndUpdateByAdmin(@RequestBody User user){
        User us = userService.saveAndUpdateByAdmin(user);
        return new ResponseEntity<>(us, HttpStatus.CREATED);
    }

    @GetMapping("/all/find-user-by-id")
    public ResponseEntity<?> findUserById(@RequestParam Long id){
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/admin/get-all-user")
    public ResponseEntity<?> getAllUser(@RequestParam String userName, Pageable pageable){
        Page<User> page = userService.getAllUser(userName,pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/admin/filter")
    public ResponseEntity<?> filterUser(FilterUserRequest request, Pageable pageable) {
        Page<User> page = userService.filterUser(request,pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}
