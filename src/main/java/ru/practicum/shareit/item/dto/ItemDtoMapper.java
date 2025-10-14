package ru.practicum.shareit.item.dto;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.model.Item;

@Component
public class ItemDtoMapper {
    public ItemDto toDto(Item item) {
        return new ItemDto(item.getName(), item.getDescription(), item.getAvailable());
    }

    public Item toItem(ItemDto itemDto, Integer ownerId) {
        var item = new Item();
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setAvailable(itemDto.getAvailable());
        item.setOwnerId(ownerId);
        return item;
    }

    public Item toItem(ItemCreateDto itemCreateDto, Integer ownerId) {
        var item = new Item();
        item.setName(itemCreateDto.getName());
        item.setDescription(itemCreateDto.getDescription());
        item.setAvailable(itemCreateDto.getAvailable());
        item.setOwnerId(ownerId);
        return item;
    }
}
