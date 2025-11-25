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

    // importing cartservice
    private static CartService cartService = new CartService();
    // importing couponservice
    private static CouponService couponService = new CouponService();
    // importing gst service
    private static GSTService gstService = new GSTService();
    // importing price service
    private static PriceService priceService = new PriceService(couponService, gstService);

    // return the item with the help of the code
    private static Item getItemByCode(String code, Item... items) {
        for (Item i : items) {
            if (i.getCode().equalsIgnoreCase(code)) return i;
        }
        return null;
    }

    // returns the coupon by the code
    private static Coupon getCouponByCode(String code, Coupon... coupons) {
        for (Coupon c : coupons) {
            if (c.getCode().equalsIgnoreCase(code)) return c;
        }
        return null;
    }

    // printing items for the user
    private static void printItems(Item[] items) {
        System.out.println("\n======= Available Items =======");
        for (Item i : items) {
            System.out.println(i.getCode() + " - " + i.getName() +
                    " | ₹" + i.getPrice() + " | GST: " + i.getGstPercent() + "%");
        }
    }

    // printing coupons
    private static void printCoupons(Coupon[] coupons) {
        System.out.println("\n======= Available Coupons =======");
        for (Coupon c : coupons) {
            System.out.println(c.getCode() + " - " + c.getType() +
                    " | Value: " + c.getValue() +
                    " | Min Cart: ₹" + c.getMinCartValue() +
                    " | Expiry: " + c.getExpiry());
        }
    }

    // printing the cart
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

    // showing bill
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

        Item ipad = new Item("I02", "iPad Air", 65000, 18);
        Item airpods = new Item("A12", "AirPods Pro", 24990, 18);
        Item keyboard = new Item("A13", "Wireless Keyboard", 4500, 18);
        Item mouse = new Item("A14", "Wireless Mouse", 2500, 18);
        Item watch = new Item("W01", "Apple Watch Series 9", 41990, 18);
        Item tv = new Item("T01", "Smart TV 55-inch", 52000, 28);
        Item speaker = new Item("S01", "Bluetooth Speaker", 3500, 18);
        Item powerBank = new Item("P01", "Power Bank 20000mAh", 2200, 18);
        Item laptopBag = new Item("B01", "Laptop Bag", 1500, 12);
        Item screenGuard = new Item("A15", "Screen Guard", 299, 12);
        Item usbCable = new Item("C23", "USB-C Cable", 799, 18);

        Item[] allItems = {
                iphone, caseCover, charger, macbook,
                ipad, airpods, keyboard, mouse,
                watch, tv, speaker, powerBank,
                laptopBag, screenGuard, usbCable
        };

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

// Additional coupons
        Coupon festival10 = new Coupon(
                "FEST10",
                Coupon.Type.PERCENT,
                10,
                5000,
                LocalDate.now().plusDays(15)
        );

        Coupon flat500 = new Coupon(
                "FLAT500",
                Coupon.Type.FLAT,
                500,
                1500,
                LocalDate.now().plusDays(7)
        );

        Coupon mega20 = new Coupon(
                "MEGA20",
                Coupon.Type.PERCENT,
                20,
                10000,
                LocalDate.now().plusDays(20)
        );

        Coupon welcome150 = new Coupon(
                "WELCOME150",
                Coupon.Type.FLAT,
                150,
                500,
                LocalDate.now().plusDays(30)
        );

        Coupon[] allCoupons = {
                newuser, percent5,
                festival10, flat500,
                mega20, welcome150
        };


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