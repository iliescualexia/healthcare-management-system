package org.example.springhealthcaremanagement.controllers;

import lombok.RequiredArgsConstructor;
import org.example.springhealthcaremanagement.dtos.user.UserDto;
import org.example.springhealthcaremanagement.dtos.user.UserRequestDto;
import org.example.springhealthcaremanagement.services.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.example.springhealthcaremanagement.globals.UrlMapping.*;

@RequestMapping(USER)
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping(FIND_USER_BY_USERNAME)
    public ResponseEntity<UserRequestDto> findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }
    @PutMapping
    public UserDto update(@RequestBody UserRequestDto userRequestDto) {
        return userService.update(userRequestDto);
    }
}
