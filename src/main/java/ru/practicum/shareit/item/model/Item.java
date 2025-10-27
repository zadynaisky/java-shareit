package ru.practicum.shareit.item.model;

import lombok.Data;

/**
 * TODO Sprint add-controllers.
 */
@Data
public class Item implements Comparable<Item> {
    private int id;
    private String name;
    private String description;
    private Integer ownerId;
    private Boolean available;
    private Integer request;

    @Override
    public int compareTo(Item o) {
        return Integer.compare(id, o.id);
    }
}
