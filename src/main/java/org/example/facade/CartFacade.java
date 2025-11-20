package org.example.facade;

import org.example.model.CartItem;
import org.example.model.Coupon;
import org.example.model.Item;
import org.example.service.CartService;
import org.example.service.CouponService;
import org.example.service.GSTService;
import org.example.service.PriceService;

import java.util.Collection;
import java.util.Map;

public class CartFacade {

    private final CartService cartService;
    private final CouponService couponService;
    private final GSTService gstService;
    private final PriceService priceService;

    public CartFacade() {
        this.cartService = new CartService();
        this.couponService = new CouponService();
        this.gstService = new GSTService();
        this.priceService = new PriceService(couponService, gstService);
    }

    public void addItem(Item item, int qty) {
        cartService.addItem(item, qty);
    }

    public void removeItem(String itemCode) {
        cartService.removeItem(itemCode);
    }

    public void reduceItem(Item item, int qty) {
        cartService.reduceItem(item, qty);
    }

    public double getSubtotal() {
        return cartService.getSubtotal();
    }

    public Map<String, CartItem> getCartItems() {
        return cartService.getItems();
    }

    public double getFinalTotal(Coupon coupon) {
        Collection<CartItem> items = cartService.getItems().values();
        return priceService.calculateFinalTotal(items, coupon);
    }

    public void clear() {
        cartService.clear();
    }
}

