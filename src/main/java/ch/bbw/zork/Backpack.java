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
}