package org.example;

import org.example.model.CartItem;
import org.example.model.Coupon;
import org.example.model.Item;
import org.example.service.CartService;
import org.example.service.CouponService;
import org.example.service.GSTService;
import org.example.service.PriceService;
import org.example.payment.PaymentResult;
import org.example.payment.PaymentService;
import org.example.payment.CardPaymentService;
import org.example.payment.UPIPaymentService;
import org.example.payment.CashPaymentService;
import org.example.payment.PaymentReceipt;
import org.example.payment.PaymentMethod;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static CartService cartService = new CartService();
    private static CouponService couponService = new CouponService();
    private static GSTService gstService = new GSTService();
    private static PriceService priceService = new PriceService(couponService, gstService);

    private static Item getItemByCode(String code, Item... items) {
        for (Item i : items) {
            if (i.getCode().equalsIgnoreCase(code)) return i;
        }
        return null;
    }

    private static Coupon getCouponByCode(String code, Coupon... coupons) {
        for (Coupon c : coupons) {
            if (c.getCode().equalsIgnoreCase(code)) return c;
        }
        return null;
    }

    private static void printItems(Item[] items) {
        System.out.println("\n======= Available Items =======");
        for (Item i : items) {
            System.out.println(i.getCode() + " - " + i.getName() +
                    " | ₹" + i.getPrice() + " | GST: " + i.getGstPercent() + "%");
        }
    }

    private static void printCoupons(Coupon[] coupons) {
        System.out.println("\n======= Available Coupons =======");
        for (Coupon c : coupons) {
            System.out.println(c.getCode() + " - " + c.getType() +
                    " | Value: " + c.getValue() +
                    " | Min Cart: ₹" + c.getMinCartValue() +
                    " | Expiry: " + c.getExpiry());
        }
    }

    private static void printCart(Map<String, CartItem> cart) {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.println("\n======= Your Cart =======");
        for (CartItem ci : cart.values()) {
            System.out.println(ci.getItem().getName() + " x " + ci.getQuantity() +
                    " = ₹" + ci.getTotalPrice());
        }
    }

    private static void showBill(CartService cartService, PriceService priceService, Coupon appliedCoupon) {
        double subtotal = cartService.getSubtotal();
        double discount = couponService.getDiscount(appliedCoupon,subtotal);
        double gst = gstService.calculateTotalGST(cartService.getItems().values());
        double finalTotal = subtotal - discount + gst;

        System.out.println("\n======= Final Bill =======");
        System.out.println("Subtotal : ₹" + subtotal);
        System.out.println("Discount : ₹" + discount);
        System.out.println("GST      : ₹" + gst);
        System.out.println("-------------------------");
        System.out.println("Final Total : ₹" + finalTotal);
    }

    public static void main(String[] args) {

        // ------------------------
        // HARD-CODED ITEMS
        // ------------------------
        Item iphone = new Item("I01", "iPhone 15", 80000, 18);
        Item caseCover = new Item("A11", "Case Cover", 600, 12);
        Item charger = new Item("C22", "Fast Charger", 1800, 18);
        Item macbook = new Item("M01", "MacBook Air", 100250, 28);

        Item[] allItems = {iphone, caseCover, charger,macbook};

        // ------------------------
        // HARD-CODED COUPONS
        // ------------------------
        Coupon newuser = new Coupon(
                "NEW1000",
                Coupon.Type.FLAT,
                1000,
                2000,
                LocalDate.now().plusDays(5)
        );

        Coupon percent5 = new Coupon(
                "SAVE5",
                Coupon.Type.PERCENT,
                5,
                1000,
                LocalDate.now().plusDays(10)
        );

        Coupon[] allCoupons = {newuser, percent5};

        Coupon appliedCoupon = null;
        Scanner sc = new Scanner(System.in);

        // ------------------------
        // MENU LOOP
        // ------------------------
        while (true) {
            System.out.println("\n===============================");
            System.out.println("       SHOPPING CART MENU");
            System.out.println("===============================");
            System.out.println("1. View Item");
            System.out.println("2. Add Item to Cart");
            System.out.println("3. Reduce quantity");
            System.out.println("4. Remove item completely");
            System.out.println("5. View All Coupon");
            System.out.println("6. Apply coupon");
            System.out.println("7. Remove coupon");
            System.out.println("8. View cart");
            System.out.println("9. View final bill");
            System.out.println("10. Choose Payment Type");
            System.out.println("11. Clear cart");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    printItems(allItems);
                    break;

                case 2:
                    printItems(allItems);
                    System.out.print("\nEnter item code: ");
                    String icode = sc.next();
                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();

                    Item chosenItem = getItemByCode(icode, allItems);
                    if (chosenItem != null) {
                        cartService.addItem(chosenItem, qty);
                        System.out.println("Added " + qty + " x " + chosenItem.getName());
                    } else {
                        System.out.println("Invalid item code.");
                    }
                    break;

                case 3:
                    System.out.print("Enter item code to reduce: ");
                    String rcode = sc.next();
                    Item reduceItem = getItemByCode(rcode, allItems);

                    if (reduceItem != null) {
                        cartService.reduceItem(reduceItem, 1);
                        System.out.println("Reduced quantity by 1.");
                    } else {
                        System.out.println("Invalid code.");
                    }
                    break;

                case 4:
                    System.out.print("Enter item code to remove: ");
                    cartService.removeItem(sc.next());
                    System.out.println("Removed item completely.");
                    break;

                case 5:
                    printCoupons(allCoupons);
                    break;

                case 6:
                    printCoupons(allCoupons);
                    System.out.print("Enter coupon code to apply: ");
                    String ccode = sc.next();

                    Coupon chosen = getCouponByCode(ccode, allCoupons);

                    if (chosen != null && couponService.validateCoupon(chosen)) {
                        appliedCoupon = chosen;
                        System.out.println("Applied coupon: " + chosen.getCode());
                    } else {
                        System.out.println("Invalid or expired coupon.");
                    }
                    break;

                case 7:
                    appliedCoupon = null;
                    System.out.println("Coupon removed.");
                    break;

                case 8:
                    printCart(cartService.getItems());
                    break;

                case 9:
                    showBill(cartService, priceService, appliedCoupon);
                    break;

                case 10:
                    System.out.println("Select Payment Method:");
                    System.out.println("1. Card");
                    System.out.println("2. UPI");
                    System.out.println("3. Cash");
                    int p = sc.nextInt();
                    sc.nextLine();

                    PaymentService paymentStrategy = null;
                    PaymentMethod paymentMethod = null;

                    switch (p) {
                        case 1 -> {
                            System.out.print("Enter Card Number: ");
                            String card = sc.nextLine();
                            paymentStrategy = new CardPaymentService(card);
                            paymentMethod = PaymentMethod.CARD;
                            System.out.println("Card payment method selected.");
                        }
                        case 2 -> {
                            System.out.print("Enter UPI ID: ");
                            String upi = sc.nextLine();
                            paymentStrategy = new UPIPaymentService(upi);
                            paymentMethod = PaymentMethod.UPI;
                            System.out.println("UPI payment method selected.");
                        }
                        case 3 -> {
                            paymentStrategy = new CashPaymentService();
                            paymentMethod = PaymentMethod.CASH;
                            System.out.println("Cash payment selected.");
                        }
                        default -> System.out.println("Invalid option");
                    }

                    if (paymentStrategy != null) {
                        double subtotal = cartService.getSubtotal();
                        double finalTotal = priceService.calculateFinalTotal(
                                cartService.getItems().values(),
                                appliedCoupon
                        );
                        double gst = gstService.calculateTotalGST(cartService.getItems().values());
                        double discount = couponService.getDiscount(appliedCoupon,subtotal);

                        PaymentResult paymentResult = paymentStrategy.processPayment(finalTotal);
                        if (paymentResult != null) {
                            if (paymentResult.getStatus()) {
                                System.out.println("Payment successful.");
                                PaymentReceipt paymentReceipt = new PaymentReceipt(
                                        UUID.randomUUID().toString(),
                                        LocalDateTime.now(),
                                        paymentMethod,
                                        subtotal,
                                        gst,
                                        discount,
                                        finalTotal
                                        );
                                System.out.println(paymentReceipt.toString());
                                cartService.clear();
                                appliedCoupon = null;
                                paymentStrategy = null;
                                paymentMethod = null;
                            } else {
                                System.out.println("Payment failed.");
                            }
                        }
                    }
                    break;

                case 11:
                    cartService.clear();
                    appliedCoupon = null;
                    System.out.println("Cart cleared.");
                    break;

                case 0:
                    System.out.println("Thank you!");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}