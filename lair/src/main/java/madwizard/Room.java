package madwizard;

import java.util.ArrayList;

/**
 * Room
 */
public class Room {

    private Room north;
    private Room east;
    private Room south;
    private Room west;
    private String description;
    private ArrayList<Item> allItemInRoom;

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

    public void addItem(Item newItem) {
        allItemInRoom.add(newItem);
    }

    public ArrayList<Item> getAllItemInRoom() {
        return allItemInRoom;
    }

    Room() {
        this.description = null;
        north = null;
        east = null;
        south = null;
        west = null;
        allItemInRoom = new ArrayList<>();
    }

    Room(String descrip) {
        this.description = descrip;
        north = null;
        east = null;
        south = null;
        west = null;
        allItemInRoom = new ArrayList<>();
    }

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

    public void printAllItem() {
        for(int i = 0; i < allItemInRoom.size()-1; i++) {
            System.out.print(allItemInRoom.get(i).getName() + ", ");
        }

        System.out.println(allItemInRoom.get(allItemInRoom.size()-1).getName() + ".");
    }
}