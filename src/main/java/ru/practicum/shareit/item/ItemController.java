package ru.practicum.shareit.item;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemCreateDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.Collection;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemServiceImpl itemService;
    private final String xSharerUserIdHeader = "X-Sharer-User-Id";

    @PostMapping
    public Item addItem(@Valid @RequestBody ItemCreateDto itemCreateDto,
                        @RequestHeader(xSharerUserIdHeader) Integer userId) {
        return itemService.create(itemCreateDto, userId);
    }

    @PatchMapping("/{itemId}")
    public Item updateItem(@RequestBody ItemDto itemDto,
                           @RequestHeader(xSharerUserIdHeader) Integer userId,
                           @PathVariable Integer itemId) {
        return itemService.update(itemDto, userId, itemId);
    }

    @GetMapping("/{itemId}")
    public Item getItemById(@PathVariable Integer itemId) {
        return itemService.findById(itemId);
    }

    @GetMapping
    public Collection<Item> getItemsByUserId(@RequestHeader(xSharerUserIdHeader) Integer userId) {
        return itemService.getItemsByUserId(userId);
    }

    @GetMapping("/search")
    public Collection<Item> getSearch(@RequestParam(name = "text") String text) {
        return itemService.searchItems(text);
    }

}
