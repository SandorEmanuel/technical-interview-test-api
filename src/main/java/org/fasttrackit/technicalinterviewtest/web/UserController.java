package org.fasttrackit.technicalinterviewtest.web;

import org.fasttrackit.technicalinterviewtest.domanin.User;
import org.fasttrackit.technicalinterviewtest.exception.ResourceNotFoundException;
import org.fasttrackit.technicalinterviewtest.service.UserService;
import org.fasttrackit.technicalinterviewtest.transfer.user.CreateUserRequest;
import org.fasttrackit.technicalinterviewtest.transfer.user.UpdateUserRequest;
import org.fasttrackit.technicalinterviewtest.transfer.user.UpdateUserResultRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/results")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser (@PathVariable("id") long id) throws ResourceNotFoundException {
        User response = userService.getUser(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest request){
        User response = userService.createUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable("id") long id, @RequestBody @Valid UpdateUserRequest request) throws ResourceNotFoundException {
        userService.updateUser(id,request);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}

