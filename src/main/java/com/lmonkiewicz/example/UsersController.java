package com.lmonkiewicz.example;

import com.lmonkiewicz.example.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by lmonkiewicz on 17.03.2017.
 */
@RestController
@RequestMapping("/users")
public class UsersController {


    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(getUserStub());
    }

    private User getUserStub(){
        User user = new User();
        user.setId(1L);
        user.setFirstName("zhang");
        user.setLastName("Eric");
        user.setAge(1123);
        user.setBirthDate(LocalDateTime.now());
        user.setBirthDate2(LocalDate.now());
        return user;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Validated(User.New.class) @RequestBody User user) {
        return ResponseEntity.created(URI.create("/users/")).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        if (id == 1L) {
            int i = 3/0;
            final User user = User.builder().id(1L).firstName("Stefan").lastName("Stefanowsky").age(32).build();
            return ResponseEntity.ok(user);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
