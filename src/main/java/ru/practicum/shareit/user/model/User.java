package ru.practicum.shareit.user.model;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * TODO Sprint add-controllers.
 */
@Data
@RequiredArgsConstructor
public class User {
    private Integer id;
    private String name;
    @Email(message = "incorrect email")
    private String email;
}