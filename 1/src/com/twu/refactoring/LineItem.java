package com.twu.refactoring;

public class LineItem {
	private String description;
	private double price;
	private int quantity;

    private static final String PRINT_TEMPLATE = "%s\t%.1f\t%d\t%.1f\n";

    private static final double SALES_TAX_RATE = 0.10;

    public LineItem(String description, double price, int quantity) {
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}

    @Override
    public String toString(){
        return String.format(PRINT_TEMPLATE, description, price, quantity, totalAmountWithoutTax());
    }

    public double totalAmountWithoutTax() {
        return price * quantity;
    }

    public double getSaleTaxApplied(){
        return totalAmountWithoutTax() * SALES_TAX_RATE;
    }

    public double totalAmountWithTax() {
        return totalAmountWithoutTax() + getSaleTaxApplied();
    }
}