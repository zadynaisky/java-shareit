package ru.practicum.shareit.user;

import ru.practicum.shareit.user.dto.UserCreateDto;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

public interface UserRepository {
    User findById(Integer id);

    User create(UserCreateDto userCreateDto);

    User update(UserDto userDto, Integer userId);

    void delete(Integer userId);

    boolean isEmailExists(String email);

    boolean isUserExists(Integer userId);
}
