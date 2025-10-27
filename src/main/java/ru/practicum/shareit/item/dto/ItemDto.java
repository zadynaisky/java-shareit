package ru.practicum.shareit.item.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * TODO Sprint add-controllers.
 */
@Getter
@AllArgsConstructor
public class ItemDto {
    private String name;
    private String description;
    private Boolean available;
}
