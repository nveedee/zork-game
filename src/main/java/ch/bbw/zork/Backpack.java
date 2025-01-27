// Backpack.java
package ch.bbw.zork;

import java.util.ArrayList;

public class Backpack {
    private ArrayList<Item> items;
    private int maxWeight;
    private int currentWeight;

    public Backpack(int maxWeight) {
        this.items = new ArrayList<>();
        this.maxWeight = maxWeight;
        this.currentWeight = 0;
    }

    public boolean addItem(Item item) {
        if (currentWeight + item.getWeight() <= maxWeight) {
            items.add(item);
            currentWeight += item.getWeight();
            return true;
        } else {
            return false;
        }
    }

    public boolean removeItem(Item item) {
        if (items.remove(item)) {
            currentWeight -= item.getWeight();
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public Item mergeItems(String itemName1, String itemName2) {
        Item item1 = null;
        Item item2 = null;

        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName1)) {
                item1 = item;
            } else if (item.getName().equalsIgnoreCase(itemName2)) {
                item2 = item;
            }
        }

        if (item1 != null && item2 != null) {
            items.remove(item1);
            items.remove(item2);
            currentWeight -= (item1.getWeight() + item2.getWeight());

            // Example combination logic
            if (item1.getName().equalsIgnoreCase("Flower") && item2.getName().equalsIgnoreCase("Book")) {
                Item newItem = new Item("HerbBook", "A book with pressed herbs", 600);
                addItem(newItem);
                return newItem;
            } else if (item1.getName().equalsIgnoreCase("Knife") && item2.getName().equalsIgnoreCase("Flower")) {
                Item newItem = new Item("HerbKnife", "A knife with herbs", 400);
                addItem(newItem);
                return newItem;
            } else if (item1.getName().equalsIgnoreCase("Book") && item2.getName().equalsIgnoreCase("Knife")) {
                Item newItem = new Item("KnifeBook", "A book with a hidden knife", 800);
                addItem(newItem);
                return newItem;
            }
        }
        return null;
    }
}