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

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // ------------------------
        // HARD-CODED ITEMS
        // ------------------------
        Item iphone = new Item("I01", "iPhone 15", 80000, 18);
        Item caseCover = new Item("A11", "Case Cover", 600, 12);
        Item charger = new Item("C22", "Fast Charger", 1800, 18);

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

        // ------------------------
        // SERVICES
        // ------------------------
        CartService cartService = new CartService();
        CouponService couponService = new CouponService();
        GSTService gstService = new GSTService();
        PriceService priceService = new PriceService(couponService, gstService);

        Coupon appliedCoupon = null;
        Scanner sc = new Scanner(System.in);

        // ------------------------
        // MENU LOOP
        // ------------------------
        while (true) {
            System.out.println("\n===============================");
            System.out.println("       SHOPPING CART MENU");
            System.out.println("===============================");
            System.out.println("1. Add iPhone");
            System.out.println("2. Add Case Cover");
            System.out.println("3. Add Charger");
            System.out.println("4. Reduce quantity");
            System.out.println("5. Remove item completely");
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
                    cartService.addItem(iphone, 1);
                    System.out.println("Added: iPhone");
                    break;

                case 2:
                    cartService.addItem(caseCover, 1);
                    System.out.println("Added: Case Cover");
                    break;

                case 3:
                    cartService.addItem(charger, 1);
                    System.out.println("Added: Charger");
                    break;

                case 4:
                    System.out.print("Enter item code to reduce: ");
                    String rcode = sc.next();
                    Item reduceItem = getItemByCode(rcode, iphone, caseCover, charger);

                    if (reduceItem != null) {
                        cartService.reduceItem(reduceItem, 1);
                        System.out.println("Reduced quantity by 1.");
                    } else {
                        System.out.println("Invalid code.");
                    }
                    break;

                case 5:
                    System.out.print("Enter item code to remove: ");
                    cartService.removeItem(sc.next());
                    System.out.println("Removed item completely.");
                    break;

                case 6:
                    System.out.print("Enter coupon code: ");
                    String ccode = sc.next();

                    if (ccode.equalsIgnoreCase(newuser.getCode())) {
                        if (couponService.validateCoupon(newuser)) {
                            appliedCoupon = newuser;
                            System.out.println("Applied coupon NEW1000");
                        } else {
                            System.out.println("Coupon invalid.");
                        }
                    } else if (ccode.equalsIgnoreCase(percent5.getCode())) {
                        if (couponService.validateCoupon(percent5)) {
                            appliedCoupon = percent5;
                            System.out.println("Applied coupon SAVE5");
                        } else {
                            System.out.println("Coupon invalid.");
                        }
                    } else {
                        System.out.println("Unknown coupon.");
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
                    double subtotal = cartService.getSubtotal();
                    double finalTotal = priceService.calculateFinalTotal(
                            cartService.getItems().values(),
                            appliedCoupon
                    );

                    System.out.println("\nSubtotal: ₹" + subtotal);
                    System.out.println("Final Total: ₹" + finalTotal);
                    break;

                case 10:
                    System.out.println("Select Payment Method:");
                    System.out.println("1. Card");
                    System.out.println("2. UPI");
                    System.out.println("3. Cash");
                    int p = sc.nextInt();
                    sc.nextLine();

                    PaymentService paymentStrategy = null;

                    switch (p) {
                        case 1 -> {
                            System.out.print("Enter Card Number: ");
                            String card = sc.nextLine();
                            paymentStrategy = new CardPaymentService(card);
                            System.out.println("Card payment method selected.");
                        }
                        case 2 -> {
                            System.out.print("Enter UPI ID: ");
                            String upi = sc.nextLine();
                            paymentStrategy = new UPIPaymentService(upi);
                            System.out.println("UPI payment method selected.");
                        }
                        case 3 -> {
                            paymentStrategy = new CashPaymentService();
                            System.out.println("Cash payment selected.");
                        }
                        default -> System.out.println("Invalid option");
                    }

                    if (paymentStrategy != null) {
                        double subtotal_ = cartService.getSubtotal();
                        double finalTotal_ = priceService.calculateFinalTotal(
                                cartService.getItems().values(),
                                appliedCoupon
                        );

                        PaymentResult paymentResult = paymentStrategy.processPayment(finalTotal_);
                        if (paymentResult != null) {
                            if (paymentResult.getStatus()) {
                                System.out.println("Payment successful.");
                                cartService.clear();
                                appliedCoupon = null;
                                paymentStrategy = null;
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

    private static Item getItemByCode(String code, Item... items) {
        for (Item i : items) {
            if (i.getCode().equalsIgnoreCase(code)) return i;
        }
        return null;
    }

    private static void printCart(Map<String, CartItem> cart) {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.println("\nYour Cart:");
        for (CartItem ci : cart.values()) {
            System.out.println(ci.getItem().getName() + " x " + ci.getQuantity() +
                    " = ₹" + ci.getTotalPrice());
        }
    }
}