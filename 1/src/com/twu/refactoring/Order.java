package com.twu.refactoring;

import java.util.List;

public class Order {
    String customerName;
    String address;
    List<LineItem> itemList;

    public Order(String customerName, String address, List<LineItem> itemList) {
        this.customerName = customerName;
        this.address = address;
        this.itemList = itemList;
    }

    public String getOrderInfo(){
        return customerName + address;
    }

    public String getItemListInfo(){
        StringBuilder output = new StringBuilder();
        for (LineItem lineItem : itemList) {
            output.append(lineItem.toString());
        }
        return output.toString();
    }

    public double totalSalesTax(){
        double totalTax = 0;
        for(LineItem item:itemList){
            totalTax += item.getSaleTaxApplied();
        }
        return totalTax;
    }

    public double totalAmountWithTax(){
        double totalAmount = 0;
        for(LineItem item:itemList){
            totalAmount += item.totalAmountWithTax();
        }
        return totalAmount;
    }
}
