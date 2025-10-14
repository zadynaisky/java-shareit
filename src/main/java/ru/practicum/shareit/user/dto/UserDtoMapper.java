package ru.practicum.shareit.user.dto;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.user.model.User;

@Component
public class UserDtoMapper {
    public UserDto toDto(User user) {
        return new UserDto(user.getName(), user.getEmail());
    }

    public User toUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        return user;
    }

    public User toUser(UserCreateDto userCreateDTO) {
        User user = new User();
        user.setName(userCreateDTO.getName());
        user.setEmail(userCreateDTO.getEmail());
        return user;
    }
}
