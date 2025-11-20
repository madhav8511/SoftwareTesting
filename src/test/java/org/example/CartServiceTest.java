package org.example;

import org.example.model.CartItem;
import org.example.model.Item;
import org.example.service.CartService;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CartServiceTest {

    @Test
    void testAddItem_NewItem() {
        CartService service = new CartService();
        Item item = new Item("A1", "Apple", 10,18);

        service.addItem(item, 2);

        Map<String, CartItem> items = service.getItems();
        assertEquals(1, items.size());
        assertEquals(2, items.get("A1").getQuantity());
    }

    @Test
    void testAddItem_ExistingItemIncrements() {
        CartService service = new CartService();
        Item item = new Item("A1", "Apple", 10,18);

        service.addItem(item, 2);
        service.addItem(item, 3);

        assertEquals(5, service.getItems().get("A1").getQuantity());
    }

    @Test
    void testReduceItem_ReducesQuantity() {
        CartService service = new CartService();
        Item item = new Item("A1", "Apple", 10,18);

        service.addItem(item, 5);
        service.reduceItem(item, 2);

        assertEquals(3, service.getItems().get("A1").getQuantity());
    }

    @Test
    void testReduceItem_RemovesWhenZero() {
        CartService service = new CartService();
        Item item = new Item("A1", "Apple", 10,18);

        service.addItem(item, 3);
        service.reduceItem(item, 3);

        assertFalse(service.getItems().containsKey("A1"));
    }

    @Test
    void testRemoveItem() {
        CartService service = new CartService();
        Item item = new Item("A1", "Apple", 10,18);

        service.addItem(item, 3);
        service.removeItem("A1");

        assertTrue(service.getItems().isEmpty());
    }

    @Test
    void testGetSubtotal() {
        CartService service = new CartService();
        Item item1 = new Item("A1", "Apple", 10,18);
        Item item2 = new Item("B1", "Banana", 20,18);

        service.addItem(item1, 2); // 20
        service.addItem(item2, 1); // 20

        assertEquals(40, service.getSubtotal());
    }
}

