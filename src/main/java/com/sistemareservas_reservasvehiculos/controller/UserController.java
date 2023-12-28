package com.sistemareservas_reservasvehiculos.controller;


import com.sistemareservas_reservasvehiculos.domain.dto.UserDto;
import com.sistemareservas_reservasvehiculos.exception.BookingException;
import com.sistemareservas_reservasvehiculos.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public record UserController(
        UserService userService
) {

    @PostMapping("/create")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/all/{offset}/{limit}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> searchAll(
            @PathVariable("offset") Integer offset,
            @PathVariable("limit") Integer limit) throws BookingException {
        List<UserDto> users = userService.userList(offset, limit);
        return new ResponseEntity<>(users, HttpStatus.FOUND);
    }

    @GetMapping("/search/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> searchUser(@PathVariable("id") Integer id) throws BookingException {
        return new  ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) throws BookingException {
        userService.deleteUser(id);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> updateUser(@PathVariable("id") Integer id, @RequestBody UserDto userDto) throws BookingException {
        userService.updateUser(id, userDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
