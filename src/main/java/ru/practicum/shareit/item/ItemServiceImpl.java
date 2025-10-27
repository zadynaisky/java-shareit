package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.exceptions.NotFoundException;
import ru.practicum.shareit.exceptions.ValidationException;
import ru.practicum.shareit.item.dto.ItemCreateDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.UserService;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class ItemServiceImpl {
    private final ItemRepository itemRepository;
    private final UserService userService;

    public Item findById(Integer itemId) {
        var item = itemRepository.findById(itemId);
        if (item == null)
            throw new NotFoundException("Item not found");
        return item;
    }

    public Item create(ItemCreateDto itemCreateDto, Integer ownerId) {
        if (ownerId == null || !userService.userExists(ownerId))
            throw new NotFoundException("Owner not found");
        return itemRepository.create(itemCreateDto, ownerId);
    }

    public Item update(ItemDto itemDto, Integer ownerId, Integer itemId) {
        if (ownerId == null || !userService.userExists(ownerId))
            throw new NotFoundException("Owner not found");
        var item = itemRepository.findById(itemId);
        if (item == null)
            throw new NotFoundException("Item not found");
        if (!item.getOwnerId().equals(ownerId))
            throw new ValidationException("Owner id mismatch");
        return itemRepository.update(itemDto, itemId);
    }

    public Collection<Item> getItemsByUserId(Integer userId) {
        if (!userService.userExists(userId))
            throw new NotFoundException("User not found");
        return itemRepository.getItemsByUserId(userId);
    }

    public Collection<Item> searchItems(String text) {
        return itemRepository.searchItems(text);
    }

    public boolean isItemExists(Integer itemId) {
        return itemRepository.isItemExists(itemId);
    }
}
