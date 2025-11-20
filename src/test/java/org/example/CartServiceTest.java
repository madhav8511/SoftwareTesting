package org.example;

import org.example.model.CartItem;
import org.example.model.Item;
import org.example.service.CartService;
import org.junit.jupiter.api.BeforeEach;
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

    private CartService cartService;
    private Item apple;
    private Item banana;

    @BeforeEach
    void setUp() {
        cartService = new CartService();
        // Item(code, name, price, gstPercent)
        apple = new Item("A1", "Apple", 10.0, 5.0);
        banana = new Item("B1", "Banana", 20.0, 5.0);
    }

    @Test
    void testGetItems_emptyInitially() {
        Map<String, CartItem> items = cartService.getItems();
        assertNotNull(items);
        assertTrue(items.isEmpty(), "Cart should start empty");
    }

    @Test
    void testAddItem_newItemCreatesEntry() {
        cartService.addItem(apple, 2);
        Map<String, CartItem> items = cartService.getItems();

        assertEquals(1, items.size());
        assertTrue(items.containsKey("A1"));
        assertEquals(2, items.get("A1").getQuantity());
    }

    @Test
    void testAddItem_existingItemIncrementsQuantity() {
        cartService.addItem(apple, 2);
        cartService.addItem(apple, 3);

        assertEquals(5, cartService.getItems().get("A1").getQuantity());
    }

    @Test
    void testAddItem_invalidQuantityThrows() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> cartService.addItem(apple, 0));
        assertTrue(ex.getMessage().toLowerCase().contains("quantity"),
                "Expected an exception about quantity when adding zero or negative qty");
    }

    @Test
    void testAddItem_nullItemThrows() {
        assertThrows(IllegalArgumentException.class, () -> cartService.addItem(null, 1));
    }

    @Test
    void testReduceItem_nonExistingDoesNothing() {
        // reducing item not present should not throw and cart remains empty
        cartService.reduceItem(apple, 1);
        assertTrue(cartService.getItems().isEmpty());
    }

    @Test
    void testReduceItem_reducesQuantityAndRemovesWhenZero() {
        cartService.addItem(apple, 3);
        cartService.reduceItem(apple, 2);
        assertEquals(1, cartService.getItems().get("A1").getQuantity());

        // reduce remaining qty to zero -> should remove key
        cartService.reduceItem(apple, 1);
        assertFalse(cartService.getItems().containsKey("A1"));
    }

    @Test
    void testRemoveItem_notFoundDoesNotThrow() {
        // remove an item code that isn't in cart - should be safe
        cartService.removeItem("NON_EXISTENT");
        assertTrue(cartService.getItems().isEmpty());
    }

    @Test
    void testGetSubtotal_emptyCartIsZero() {
        assertEquals(0.0, cartService.getSubtotal(), 0.0001);
    }

    @Test
    void testGetSubtotal_withItems() {
        cartService.addItem(apple, 2);   // 2 * 10 = 20
        cartService.addItem(banana, 1);  // 1 * 20 = 20
        assertEquals(40.0, cartService.getSubtotal(), 0.0001);
    }

    @Test
    void testClear_clearsCart() {
        cartService.addItem(apple, 2);
        cartService.addItem(banana, 1);
        assertFalse(cartService.getItems().isEmpty());

        cartService.clear();
        assertTrue(cartService.getItems().isEmpty());
        assertEquals(0.0, cartService.getSubtotal(), 0.0001);
    }
}

