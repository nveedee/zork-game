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

            if (item1.getName().equalsIgnoreCase("Flower") && item2.getName().equalsIgnoreCase("Pillow")) {
                Item newItem = new Item("DreamFlower", "A flower that helps you sleep peacefully", 500);
                addItem(newItem);
                return newItem;
            } else if (item1.getName().equalsIgnoreCase("Book") && item2.getName().equalsIgnoreCase("Painting")) {
                Item newItem = new Item("ArtBook", "A book filled with beautiful paintings", 2500);
                addItem(newItem);
                return newItem;
            } else if (item1.getName().equalsIgnoreCase("Knife") && item2.getName().equalsIgnoreCase("Pillow")) {
                Item newItem = new Item("KnifePillow", "A pillow with a hidden knife", 1000);
                addItem(newItem);
                return newItem;
            } else if (item1.getName().equalsIgnoreCase("Painting") && item2.getName().equalsIgnoreCase("Flower")) {
                Item newItem = new Item("FloralPainting", "A painting adorned with pressed flowers", 2200);
                addItem(newItem);
                return newItem;
            } else if (item1.getName().equalsIgnoreCase("Pillow") && item2.getName().equalsIgnoreCase("Book")) {
                Item newItem = new Item("DreamBook", "A book that helps you dream vividly", 1200);
                addItem(newItem);
                return newItem;
            } else if (item1.getName().equalsIgnoreCase("Knife") && item2.getName().equalsIgnoreCase("Painting")) {
                Item newItem = new Item("FramedKnife", "A knife displayed in a beautiful frame", 2300);
                addItem(newItem);
                return newItem;
            }
        }
        return null;
    }

}