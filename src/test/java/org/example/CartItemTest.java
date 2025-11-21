package org.example;

import org.example.model.CartItem;
import org.example.model.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CartItemTest {

    @Test
    void constructorShouldThrowWhenItemNull() {
        assertThrows(IllegalArgumentException.class, () -> new CartItem(null, 5));
    }

    @Test
    void quantityCannotBeNegative() {
        Item item = new Item("A1", "Apple", 10.0,18);
        CartItem cart = new CartItem(item, -5);
        assertEquals(0, cart.getQuantity());
    }

    @Test
    void incrementShouldAddQuantityWhenPositive() {
        CartItem cart = new CartItem(new Item("A1", "Apple", 10,18), 2);
        cart.increment(3);
        assertEquals(5, cart.getQuantity());
    }

    @Test
    void incrementShouldDoNothingForZeroOrNegative() {
        CartItem cart = new CartItem(new Item("A1", "Apple", 10,18), 2);
        cart.increment(0);
        cart.increment(-5);
        assertEquals(2, cart.getQuantity());
    }

    @Test
    void decrementShouldNeverGoBelowZero() {
        CartItem cart = new CartItem(new Item("A1", "Apple", 10,18), 3);
        cart.decrement(10);
        assertEquals(0, cart.getQuantity());
    }

    @Test
    void totalPriceShouldBeCorrect() {
        CartItem cart = new CartItem(new Item("A1", "Apple", 10,18), 3);
        assertEquals(30.0, cart.getTotalPrice());
    }

    @Test
    void setItemShouldThrowWhenNull() {
        CartItem cart = new CartItem(new Item("A1", "Apple", 10,18), 3);
        assertThrows(IllegalArgumentException.class, () -> cart.setItem(null));
    }

    @Test
    void setQuantityShouldNotAllowNegative() {
        CartItem cart = new CartItem(new Item("A1", "Apple", 10,18), 3);
        cart.setQuantity(-10);
        assertEquals(0, cart.getQuantity());
    }
}
