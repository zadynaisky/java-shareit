package ru.practicum.shareit.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserCreateDto;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable("userId") int userId) {
        return userService.findById(userId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        return userService.create(userCreateDto);
    }

    @PatchMapping("/{userId}")
    public User updateUser(@PathVariable int userId, @Valid @RequestBody UserDto userDto) {
        return userService.update(userDto, userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userService.delete(userId);
    }


}

