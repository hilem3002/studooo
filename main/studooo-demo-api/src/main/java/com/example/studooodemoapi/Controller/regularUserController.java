package com.example.studooodemoapi.Controller;

import com.example.studooodemoapi.Model.regularUser;
import com.example.studooodemoapi.Service.regularUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regularUsers")
public class regularUserController {

    private final regularUserService regularUserService;

    public regularUserController(regularUserService regularUserService) {
        this.regularUserService = regularUserService;
    }

    // able to search users according to their username eposta and pass infos also used for log in
    // also used for username and eposta are valid
    @GetMapping
    public ResponseEntity<List<regularUser>> getRegularUsers(@RequestParam(required = false) String username, @RequestParam(required = false) String eposta) {
        return new ResponseEntity<>(regularUserService.getRegularUsers(username, eposta), HttpStatus.OK);
    }

    // adding new users to database it also called sign up
    @PostMapping
    public ResponseEntity<regularUser> signUp(@RequestBody regularUser user) {
        return new ResponseEntity<>(regularUserService.signUp(user), HttpStatus.CREATED);
    }

    // changing the user's password
    @PutMapping("/{username}")
    public ResponseEntity<regularUser> changePass(@PathVariable String username, @RequestBody regularUser user) {
        return new ResponseEntity<>(regularUserService.changePass(user), HttpStatus.CREATED);
    }


}
