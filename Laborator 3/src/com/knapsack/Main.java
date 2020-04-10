package com.knapsack;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Book book = new Book(Item.ITEM_TYPE.UNDEFINED);
        Book book2 = new Book(Item.ITEM_TYPE.UNDEFINED);
        Book book3 = new Book(Item.ITEM_TYPE.UNDEFINED);

        Food food = new Food(Item.ITEM_TYPE.UNDEFINED);
        Food food2 = new Food(Item.ITEM_TYPE.UNDEFINED);
        Food food3 = new Food(Item.ITEM_TYPE.UNDEFINED);

        Weapon weapon = new Weapon(Item.ITEM_TYPE.UNDEFINED);
        Weapon weapon2 = new Weapon(Item.ITEM_TYPE.UNDEFINED);
        Weapon weapon3 = new Weapon(Item.ITEM_TYPE.UNDEFINED);

        Knapsack knapsack = new Knapsack();

        List<Item> itemsList = new ArrayList<Item>();
        itemsList.add(book);
        itemsList.add(book2);
        itemsList.add(book3);
        itemsList.add(food);
        itemsList.add(food2);
        itemsList.add(food3);
        itemsList.add(weapon);
        itemsList.add(weapon2);
        itemsList.add(weapon3);

        Problem problem = new Problem(itemsList, knapsack);
        System.out.println(problem.toString());
    }
}
