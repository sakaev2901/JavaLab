package ru.itis.querydsl.controllers;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.querydsl.models.User;
import ru.itis.querydsl.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public ResponseEntity<List<User>> searchByRequest(@QuerydslPredicate(root = User.class) Predicate predicate) {
        return ResponseEntity.ok(
                StreamSupport.stream(userRepository.findAll(predicate).spliterator(), true)
                    .collect(Collectors.toList())
        );
    }

}
