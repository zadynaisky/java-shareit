package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.ItemCreateDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.Collection;

public interface ItemRepository {
    Item findById(Integer id);

    Item create(ItemCreateDto itemCreateDto, Integer ownerId);

    Item update(ItemDto newItemDto, Integer itemId);

    Collection<Item> getItemsByUserId(Integer userId);

    Collection<Item> searchItems(String text);

    boolean isItemExists(Integer itemId);
}
