package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dto.ItemCreateDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDtoMapper;
import ru.practicum.shareit.item.model.Item;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Boolean.TRUE;
import static java.util.Collections.EMPTY_LIST;
import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class InMemoryItemRepository implements ItemRepository {
    private final ItemDtoMapper itemDtoMapper;

    private final Map<Integer, Item> items = new HashMap<>();
    private int nextId = 1;

    @Override
    public Collection<Item> getItemsByUserId(Integer userId) {
        return items
                .values()
                .stream()
                .filter(x -> x.getOwnerId().equals(userId))
                .sorted()
                .collect(toList());
    }

    @Override
    public Collection<Item> searchItems(String text) {
        if (text == null || text.isBlank()) {
            return EMPTY_LIST;
        }

        return items
                .values()
                .stream()
                .filter(x -> TRUE.equals(x.getAvailable()))
                .filter(x -> (x.getName() != null && x.getName().toLowerCase().contains(text.toLowerCase())) ||
                        (x.getDescription() != null && x.getDescription().toLowerCase().contains(text.toLowerCase())))
                .sorted()
                .collect(toList());
    }

    @Override
    public Item findById(Integer id) {
        return items.get(id);
    }

    @Override
    public Item create(ItemCreateDto itemCreateDto, Integer ownerId) {
        //добавить проверку, что пользователь существует!
        var item = itemDtoMapper.toItem(itemCreateDto, ownerId);
        var id = getNextId();
        item.setId(id);
        items.put(id, item);
        return item;
    }

    @Override
    public Item update(ItemDto itemDto, Integer itemId) {
        var item = findById(itemId);
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setAvailable(itemDto.getAvailable());
        return item;
    }

    @Override
    public boolean isItemExists(Integer itemId) {
        return items.containsKey(itemId);
    }

    private int getNextId() {
        return nextId++;
    }
}
