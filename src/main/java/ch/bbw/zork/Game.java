package ch.bbw.zork;import java.util.ArrayList;import java.util.List;import java.util.Stack;public class Game {    private Parser parser;    private Room currentRoom;    private Stack<String[]> lastRooms;    private Room garden, library, kitchen, hall, bedroom;    private List<Room> rooms;    private Backpack backpack;    public Game() {        parser = new Parser(System.in);        lastRooms = new Stack<>();        rooms = new ArrayList<>();        backpack = new Backpack(5000);        garden = new Room("in a beautiful garden");        library = new Room("in a quiet library");        kitchen = new Room("in a cozy kitchen");        hall = new Room("in a grand hall");        bedroom = new Room("in a comfortable bedroom");        rooms.add(garden);        rooms.add(library);        rooms.add(kitchen);        rooms.add(hall);        rooms.add(bedroom);        garden.put(library, hall, kitchen, null);        library.put(null, null, garden, null);        kitchen.put(garden, null, null, null);        hall.put(null, bedroom, null, garden);        bedroom.put(null, null, null, hall);        garden.addItem(new Item("Flower", "A beautiful flower", 100));        library.addItem(new Item("Book", "An interesting book", 500));        kitchen.addItem(new Item("Knife", "A sharp knife", 300));        hall.addItem(new Item("Painting", "A valuable painting", 2000));        bedroom.addItem(new Item("Pillow", "A soft pillow", 700));        currentRoom = garden;        currentRoom.setVisited(true);    }    public void play() {        printWelcome();        boolean finished = false;        while (!finished) {            Command command = parser.get();            finished = processCommand(command);        }        System.out.println("Thank you for playing. Good bye.");    }    private void printWelcome() {        System.out.println();        System.out.println("Welcome to Zork!");        System.out.println("Zork is a simple adventure game.");        System.out.println("Type 'help' if you need help.");        System.out.println();        System.out.println(currentRoom.longDescription());    }    private boolean processCommand(Command command) {        if (command.isUnknown()) {            System.out.println("I don't know what you mean...");            return false;        }        String commandWord = command.getCommandWord();        if (commandWord.equals("help")) {            printHelp();        } else if (commandWord.equals("go")) {            goRoom(command);        } else if (commandWord.equals("quit")) {            if (command.hasSecondWord())                System.out.println("Quit what?");            else                return true;        } else if (commandWord.equals("back")) {            if (lastRooms.isEmpty()) {                System.out.println("No previous room to go back to.");            } else {                String[] lastRoom = lastRooms.pop();                currentRoom = getRoomByDescription(lastRoom[0]);                System.out.println("You are back in " + currentRoom.shortDescription());            }        } else if (commandWord.equals("map")) {            printMap();        } else if (commandWord.equals("items")) {            printItems();        } else if (commandWord.equals("take")) {            takeItem(command);        } else if (commandWord.equals("drop")) {            dropItem(command);        } else if (commandWord.equals("backpack")) {            printBackpack();        } else if (commandWord.equals("merge")) {            mergeItems(command);        }        return false;    }    private void printMap() {        System.out.println("Map of visited rooms:");        for (Room room : rooms) {            if (room.isVisited()) {                System.out.println(room.shortDescription());            }        }    }    private void printItems() {        System.out.println("Items in the current room:");        for (Item item : currentRoom.getItems()) {            System.out.println(item);        }    }    private void printBackpack() {        System.out.println("Items in the backpack:");        for (Item item : backpack.getItems()) {            System.out.println(item);        }        System.out.println("Current weight: " + backpack.getCurrentWeight() + "g");        System.out.println("Max weight: " + backpack.getMaxWeight() + "g");    }    private void takeItem(Command command) {        if (!command.hasSecondWord()) {            System.out.println("Take what?");        } else {            String itemName = command.getSecondWord();            Item itemToTake = null;            for (Item item : currentRoom.getItems()) {                if (item.getName().equalsIgnoreCase(itemName)) {                    itemToTake = item;                    break;                }            }            if (itemToTake == null) {                System.out.println("There is no such item here.");            } else {                if (backpack.addItem(itemToTake)) {                    currentRoom.getItems().remove(itemToTake);                    System.out.println("You have taken the " + itemName + ".");                } else {                    System.out.println("Your backpack is too heavy to take this item.");                }            }        }    }    private void dropItem(Command command) {        if (!command.hasSecondWord()) {            System.out.println("Drop what?");        } else {            String itemName = command.getSecondWord();            Item itemToDrop = null;            for (Item item : backpack.getItems()) {                if (item.getName().equalsIgnoreCase(itemName)) {                    itemToDrop = item;                    break;                }            }            if (itemToDrop == null) {                System.out.println("You don't have such an item in your backpack.");            } else {                if (backpack.removeItem(itemToDrop)) {                    currentRoom.addItem(itemToDrop);                    System.out.println("You have dropped the " + itemName + ".");                } else {                    System.out.println("You cannot drop this item.");                }            }        }    }    private void mergeItems(Command command) {        if (!command.hasSecondWord()) {            System.out.println("Merge what?");        } else {            String[] itemsToMerge = command.getSecondWord().split(",");            if (itemsToMerge.length != 2) {                System.out.println("You need to specify two items to merge.");            } else {                String itemName1 = itemsToMerge[0].trim();                String itemName2 = itemsToMerge[1].trim();                Item newItem = backpack.mergeItems(itemName1, itemName2);                if (newItem != null) {                    System.out.println("You have merged " + itemName1 + " and " + itemName2 + " into " + newItem.getName() + ".");                } else {                    System.out.println("These items cannot be merged.");                }            }        }    }    private void printHelp() {        System.out.println("You are lost. You are alone. You wander");        System.out.println("around in a mysterious place.");        System.out.println();        System.out.println("Your command words are:");        System.out.println(parser.showCommands());    }    private void goRoom(Command command) {        if (!command.hasSecondWord()) {            System.out.println("Go where?");        } else {            String direction = command.getSecondWord();            Room nextRoom = currentRoom.nextRoom(direction);            if (nextRoom == null)                System.out.println("There is no door!");            else {                lastRooms.push(new String[]{currentRoom.shortDescription(), direction});                currentRoom = nextRoom;                currentRoom.setVisited(true);                System.out.println(currentRoom.longDescription());                System.out.println("You are here.");                if (currentRoom.equals(bedroom)) {                    System.out.println("Congratulations! You have won the game.");                    System.exit(0);                }            }        }    }    private Room getRoomByDescription(String description) {        for (Room room : rooms) {            if (room.shortDescription().equals(description)) {                return room;            }        }        return null;    }}