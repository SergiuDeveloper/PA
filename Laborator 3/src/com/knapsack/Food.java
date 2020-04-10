package com.knapsack;

public class Food implements Item {
    public Food(ITEM_TYPE name) {
        this.name = name;
    }

    private ITEM_TYPE name = null;
    public String getName() {
        switch (this.name) {
        }

        return "Undefined";
    }
    private void setName(ITEM_TYPE name) {
        this.name = name;
    }

    private double weight = 0;
    private double getWeight() {
        return this.weight;
    }
    private void setWeight(double weight) {
        this.weight = weight;
    }

    private double value = 0;
    private double getValue() {
        return this.value;
    }
    private void setValue(double value) {
        this.value = value;
    }

    public double profitFactor() {
        return this.value / this.weight;
    }
}
