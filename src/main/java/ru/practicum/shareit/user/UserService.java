package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exceptions.NotFoundException;
import ru.practicum.shareit.user.dto.UserCreateDto;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userStorage;

    public User create(UserCreateDto userCreateDto) {
        return userStorage.create(userCreateDto);
    }

    public User update(UserDto userDto, Integer userId) {
        if (!userExists(userId))
            throw new NotFoundException("User not found");
        return userStorage.update(userDto, userId);
    }

    public User findById(Integer userId) {
        var user = userStorage.findById(userId);
        if (user == null)
            throw new NotFoundException("User not found");
        return user;
    }

    public void delete(Integer userId) {
        userStorage.delete(userId);
    }

    public boolean userExists(Integer userId) {
        return userStorage.isUserExists(userId);
    }
}