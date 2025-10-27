package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemCreateDto {
    @NotBlank(message = "name cannot be empty")
    private String name;
    @NotBlank(message = "description cannot be empty")
    private String description;
    @NotNull(message = "available cannot be null")
    private Boolean available;
}
