package com.hedi.api;

import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/users")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @RequestMapping("/users/{id}")
    public Optional<User> findOne(@PathVariable("id") String id) {
        return userRepository.findById(id);
    }

    @RequestMapping(value = "/users/search", method = RequestMethod.POST)
    public Optional<User> search(@RequestBody SearchUserDto query) {

        if (!ObjectUtils.isEmpty(query.username)) {
            return userRepository.findByUsername(query.username);
        }

        return Optional.empty();
    }

    @RequestMapping(value = "/users/login", method = RequestMethod.POST)
    public User login(@RequestBody SearchUserDto query) {

        Optional<User> result = userRepository.findByUsernameAndPasswordAndIsActive(query.username, query.password,
                true);

        if (result.isPresent()) {
            User user = result.get();
            user.setAuthenticated(true);
            user.setLastLoginDate(LocalDateTime.now());

            userRepository.save(user);

            return user;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "not authorized");
        }
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User save(User user) {
        return userRepository.insert(user);
    }
}
