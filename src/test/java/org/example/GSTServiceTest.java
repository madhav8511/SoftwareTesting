package org.example;

import org.example.model.CartItem;
import org.example.model.Item;
import org.example.service.GSTService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GSTServiceTest {

    @Test
    void testCalculateGST() {
        GSTService service = new GSTService();

        Item item1 = new Item("A1", "Apple", 100, 5); // 5% GST
        Item item2 = new Item("B1", "Phone", 20000, 18); // 18% GST

        CartItem c1 = new CartItem(item1, 2); // 200 → 10 GST
        CartItem c2 = new CartItem(item2, 1); // 20000 → 3600 GST

        double gst = service.calculateTotalGST(List.of(c1, c2));

        assertEquals(3610, gst);
    }
}
