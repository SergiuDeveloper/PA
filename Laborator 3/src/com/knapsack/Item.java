package com.knapsack;

public interface Item {
    enum ITEM_TYPE { UNDEFINED }

    ITEM_TYPE name = null;
    double value = 0;
    double weight = 0;

    public String getName();

    double profitFactor();
}
