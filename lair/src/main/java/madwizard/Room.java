package madwizard;

import java.util.ArrayList;

/**
 * The Room class represents a room that a player can enter
 * in the lair. The room contains a list of items that the 
 * player can grab or interact with.
 */
public class Room {

    private Room north;                     // Path to north room.
    private Room east;                      // Path to east room.
    private Room south;                     // Path to south room.
    private Room west;                      // Path to west room.
    private String description;             // "Lore" description of the room.
    private ArrayList<Item> allItemInRoom;  // ArrayList that contains all the items stored in the room.

    // Getters and Setters
    public Room getNorth() {
        return north;
    }

    public Room getEast() {
        return east;
    }

    public Room getSouth() {
        return south;
    }

    public Room getWest() {
        return west;
    }

    public String getDescription() {
        return description;
    }

    public void setNorth(Room north) {
        this.north = north;
    }

    public void setEast(Room east) {
        this.east = east;
    }

    public void setSouth(Room south) {
        this.south = south;
    }

    public void setWest(Room west) {
        this.west = west;
    }

    public ArrayList<Item> getAllItemInRoom() {
        return allItemInRoom;
    }

    // Add item into the room. Used for when
    // a player drops an item.
    public void addItem(Item newItem) {
        allItemInRoom.add(newItem);
    }

    // Remove an item from the room. Used for
    // when an item is grabbed by the player.
    public void removeItem(int index) {
        allItemInRoom.remove(index);
    }

    // Remove an item from the room. Used for
    // when an item is grabbed by the player.
    public void removeItem(Item i) {
        allItemInRoom.remove(i);
    }


    Room(String descrip) {
        this.description = descrip;
        north = null;
        east = null;
        south = null;
        west = null;
        allItemInRoom = new ArrayList<>();
    }

    // Used to turn a locked room into an unlocked room.
    Room(LockedRoom unlocked) {
        description = unlocked.getDescription();
        north = unlocked.getNorth();
        east = unlocked.getEast();
        south = unlocked.getSouth();
        west = unlocked.getWest();
        allItemInRoom = new ArrayList<>();
        for(Item i: unlocked.getAllItemInRoom()) {
            allItemInRoom.add(i);
        }
    }

    // printArea() will print the paths that the room
    // contains to other rooms in a box visual representation.
    public void printArea() {

        System.out.println("-------");
        if(north != null) System.out.println("|  ^  |");
        else System.out.println("|     |");
        
        if(west != null) System.out.print("|< X");
        else System.out.print("|  X");

        if(east != null) System.out.println(" >|");
        else System.out.println("  |");

        if(south != null) System.out.println("|  v  |");
        else System.out.println("|     |");

        System.out.println("-------");
    }

    // Used to print all items in the room into a narrative sentence.
    // EX: "warbanner, magnifing glass, blade." 
    public void printAllItems() {
        
        if(allItemInRoom.size() < 1) {
            System.out.println("Nothing but disappointment.");
            return;
        }

        for(int i = 0; i < allItemInRoom.size()-1; i++) {
            System.out.print("\"" + allItemInRoom.get(i).getName() + "\", ");
        }

        System.out.println("\"" + allItemInRoom.get(allItemInRoom.size()-1).getName() + "\".");
    }

    // Used to print all items in the room into a list format.
    public void listAllItems() {
        for(int i = 0; i < allItemInRoom.size(); i++) {
            System.out.println((i+1) + " -> " + allItemInRoom.get(i).getName());
        }
    }
}