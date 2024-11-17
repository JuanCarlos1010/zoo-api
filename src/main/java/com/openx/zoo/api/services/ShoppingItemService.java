package com.openx.zoo.api.services;

import com.openx.zoo.api.entities.Food;
import com.openx.zoo.api.entities.Shopping;
import com.openx.zoo.api.entities.ShoppingItem;
import com.openx.zoo.api.exceptions.InternalServerException;
import com.openx.zoo.api.exceptions.NotFoundException;
import com.openx.zoo.api.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingItemService {
    private final ShoppingItemRepository shoppingItemRepository;
    private final FoodRepository foodRepository;
    private final ShoppingRepository shoppingRepository;

    public ShoppingItemService(FoodRepository foodRepository, ShoppingItemRepository shoppingItemRepository, ShoppingRepository shoppingRepository) {
        this.foodRepository = foodRepository;
        this.shoppingItemRepository = shoppingItemRepository;
        this.shoppingRepository = shoppingRepository;
    }

    public List<ShoppingItem> findAllShoppingItems() {
        try {
            return shoppingItemRepository.findAll();
        } catch (Exception e) {
            throw new InternalServerException(e);
        }
    }

    public ShoppingItem getShoppingItemById(Long id) {
        return shoppingItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Detalle de compra no encontrado en el id: " + id));
    }

    @Transactional
    public ShoppingItem createShoppingItem(ShoppingItem shoppingItem) {
        Food food = shoppingItem.getFood();
        Shopping shopping = shoppingItem.getShopping();
        if ((food == null) && (shopping == null)) {
            throw new NotFoundException("El campo alimento o compra es obligatorio");
        } else {
            Optional<Food> foodOpt = foodRepository.findById(shoppingItem.getId());
            if (foodOpt.isEmpty()) {
                assert shopping != null;
                throw new NotFoundException("Alimento no encontrado con id: " + shopping.getId());
            } else {
                Optional<Shopping> shoppingOpt = shoppingRepository.findById(shoppingItem.getId());
                if (shoppingOpt.isEmpty()) {
                    throw new NotFoundException("Compra no encontrado con id: " + shopping.getId());
                }
                shoppingItem.setFood(foodOpt.get());
                shoppingItem.setShopping(shoppingOpt.get());
                return shoppingItemRepository.save(shoppingItem);
            }
        }
    }

    @Transactional
    public ShoppingItem updateShoppingItem(ShoppingItem updateshoppingItem) {
        Food food = updateshoppingItem.getFood();
        Shopping shopping = updateshoppingItem.getShopping();
        if ((food == null) && (shopping == null)) {
            throw new NotFoundException("El campo alimento o compra es obligatorio");
        }
        return shoppingItemRepository.findById(updateshoppingItem.getId())
                .map(shoppingItem -> {
                    shoppingItem.setQuantity(updateshoppingItem.getQuantity());
                    shoppingItem.setPrice(updateshoppingItem.getPrice());
                    shoppingItem.setFood(food);
                    shoppingItem.setShopping(shopping);
                    return shoppingItemRepository.save(shoppingItem);
                })
                .orElseThrow(() -> new NotFoundException("Detalle de compra no encontrada con el id: " + updateshoppingItem.getId()));
    }

    @Transactional
    public boolean deleteShoppingItem(Long id) {
        ShoppingItem shoppingItem = getShoppingItemById(id);
        shoppingItemRepository.delete(shoppingItem);
        return true;
    }
}
