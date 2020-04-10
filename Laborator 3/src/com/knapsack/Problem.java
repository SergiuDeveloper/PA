package com.knapsack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Problem {
    public Problem(List<Item> itemsList, Knapsack knapsack) {
        this.itemsList = itemsList;
        this.knapsack = knapsack;
    }
    public Problem() {
        this.itemsList = new ArrayList<Item>();
    }

    private List<Item> itemsList;
    private List<Item> getItemsList() {
        return this.itemsList;
    }
    private void setItemsList(List<Item> itemsList) {
        this.itemsList = itemsList;
    }

    private Knapsack knapsack;
    private Knapsack getKnapsack() {
        return this.knapsack;
    }
    private void setKnapsack(Knapsack knapsack) {
        this.knapsack = knapsack;
    }

    @Override
    public String toString() {
        String outputString = "Instance:\n";
        this.itemsList.sort(new ItemsComparator());
        for (var item : this.itemsList)
            outputString += item.getName() + " ";
        outputString += "\n\n";

        return outputString;
    }

    private class ItemsComparator implements Comparator<Item> {
        public int compare(Item a, Item b) {
            String aName = a.getName();
            String bName = b.getName();

            for (int i = 0; i < Math.min(aName.length(), bName.length()); ++i)
                if (aName.charAt(i) > bName.charAt(i))
                    return -1;
                else if (bName.charAt(i) < bName.charAt(i))
                    return 1;

            if (aName.length() == bName.length())
                return 0;
            if (aName.length() > bName.length())
                return -1;
            return 1;
        }
    }
}
