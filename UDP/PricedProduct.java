/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP;

import java.io.Serializable;

public class PricedProduct implements Serializable {

    private static final long serialVersionUID = 20260517L;

    private String id;
    private String name;
    private double basePrice;
    private double taxRate;
    private double discountRate;
    private double finalPrice;

    public PricedProduct(String id,
                         String name,
                         double basePrice,
                         double taxRate,
                         double discountRate,
                         double finalPrice) {

        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.taxRate = taxRate;
        this.discountRate = discountRate;
        this.finalPrice = finalPrice;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }
}