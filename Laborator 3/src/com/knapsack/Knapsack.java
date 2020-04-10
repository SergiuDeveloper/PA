package com.knapsack;

import java.util.ArrayList;
import java.util.List;

public class Knapsack {
    public Knapsack() {
        this.itemList = new ArrayList<Item>();
    }

    private double totalWeight;
    private double getTotalWeight() {
        return this.totalWeight;
    }
    private void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    private List<Item> itemList;
    private List<Item> getItemList() {
        return this.itemList;
    }
    private void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public boolean addItem(Item item) {
        if (this.itemList.contains(item))
            return false;

        this.itemList.add(item);
        this.totalWeight += item.weight;
        return true;
    }
}
