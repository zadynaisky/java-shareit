package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.exceptions.EmailAlreadyExistsException;
import ru.practicum.shareit.exceptions.NotFoundException;
import ru.practicum.shareit.exceptions.ValidationException;
import ru.practicum.shareit.user.dto.UserCreateDto;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserDtoMapper;
import ru.practicum.shareit.user.model.User;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class InMemoryUserRepository implements UserRepository {
    private final UserDtoMapper userDtoMapper;

    private final Map<Integer, User> users = new HashMap<>();
    private int nextId = 1;

    @Override
    public User findById(Integer id) {
        if (id == null) {
            throw new ValidationException("id cannot be null");
        }
        if (!users.containsKey(id)) {
            log.info("User not found with id {}", id);
            throw new NotFoundException(String.format("User not found with id %s", id));
        }
        return users.get(id);
    }

    @Override
    public User create(UserCreateDto userCreateDto) {
        if (isEmailExists(userCreateDto.getEmail()))
            throw new EmailAlreadyExistsException("email already exists");
        var user = userDtoMapper.toUser(userCreateDto);
        user.setId(getNextId());
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User update(UserDto userDto, Integer userId) {
        var user = users.get(userId);
        if (userDto.getName() != null)
            user.setName(userDto.getName());
        if (userDto.getEmail() != null) {
            if (!user.getEmail().equals(userDto.getEmail()) && isEmailExists(userDto.getEmail()))
                throw new EmailAlreadyExistsException("email already exists");
            user.setEmail(userDto.getEmail());
        }
        return user;
    }

    @Override
    public void delete(Integer userId) {
        users.remove(userId);
    }

    @Override
    public boolean isEmailExists(String email) {
        return users.values().stream().anyMatch(user -> user.getEmail().toLowerCase().equals(email.toLowerCase()));
    }

    @Override
    public boolean isUserExists(Integer userId) {
        return users.containsKey(userId);
    }

    private int getNextId() {
        return nextId++;
    }
}
