package org.example;

import org.example.model.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    @Test
    void testConstructorValidInput() {
        Item item = new Item("A1", "Apple", 10.0, 5.0);

        assertEquals("A1", item.getCode());
        assertEquals("Apple", item.getName());
        assertEquals(10.0, item.getPrice());
        assertEquals(5.0, item.getGstPercent());
    }

    @Test
    void testConstructorThrowsWhenCodeNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Item(null, "Apple", 10.0, 5.0));
    }

    @Test
    void testConstructorThrowsWhenCodeEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> new Item("", "Apple", 10.0, 5.0));
    }

    @Test
    void testSetCodeValid() {
        Item item = new Item("A1", "Apple", 10.0, 5.0);
        item.setCode("B2");
        assertEquals("B2", item.getCode());
    }

    @Test
    void testSetCodeThrowsWhenNull() {
        Item item = new Item("A1", "Apple", 10.0, 5.0);

        assertThrows(IllegalArgumentException.class,
                () -> item.setCode(null));
    }

    @Test
    void testSetCodeThrowsWhenEmpty() {
        Item item = new Item("A1", "Apple", 10.0, 5.0);

        assertThrows(IllegalArgumentException.class,
                () -> item.setCode(""));
    }

    @Test
    void testSetNameWorksForNull() {
        Item item = new Item("A1", "Apple", 10.0, 5.0);
        item.setName(null);
        assertNull(item.getName());
    }

    @Test
    void testSetNameWorksForEmptyString() {
        Item item = new Item("A1", "Apple", 10.0, 5.0);
        item.setName("");
        assertEquals("", item.getName());
    }

    @Test
    void testSetPrice() {
        Item item = new Item("A1", "Apple", 10.0, 5.0);
        item.setPrice(20.5);
        assertEquals(20.5, item.getPrice());
    }

    @Test
    void testSetGstPercent() {
        Item item = new Item("A1", "Apple", 10.0, 5.0);
        item.setGstPercent(18.0);
        assertEquals(18.0, item.getGstPercent());
    }

    @Test
    void testToStringContainsFields() {
        Item item = new Item("A1", "Apple", 10.0, 5.0);

        String output = item.toString();

        assertTrue(output.contains("A1"));
        assertTrue(output.contains("Apple"));
        assertTrue(output.contains("10.0"));
        assertTrue(output.contains("5.0"));
    }
}
