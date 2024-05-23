package madwizard;

import java.util.ArrayList;
import java.util.Scanner;

import madwizard.Lair.Direction;

public class Player {

    ArrayList<Item> inventory = new ArrayList<>();
    
    // tryDirection() trys out a direction given by the player.
    // If its a valid move, return the room that the user requested,
    // else it returns the room the player is currently in.
    public Room tryDirection(Room start, Direction direction) {
        switch(direction) {
            case NORTH:
                if(start.getNorth() == null) { 
                    System.out.println("No access to that direction.");
                    return start;
                }
                else {
                    System.out.println("You proceed to the northern room...");
                    return start.getNorth();
                }
            case EAST:
                if(start.getEast() == null) { 
                    System.out.println("No access to that direction.");
                    return start;
                }
                else {
                    System.out.println("You proceed to the eastern room...");
                    return start.getEast();
                }
            case SOUTH:
                if(start.getSouth() == null) { 
                    System.out.println("No access to that direction.");
                    return start;
                }
                else {
                    System.out.println("You proceed to the southern room...");
                    return start.getSouth();
                }
            case WEST:
                if(start.getWest() == null) { 
                    System.out.println("No access to that direction.");
                    return start;
                }
                else {
                    System.out.println("You proceed to the western room...");
                    return start.getWest();
                }
            default:
                throw new RuntimeException("move() received no direction.");
        }
    }

    // move() allows the user to jump between rooms in the lair.
    // Asks the player for the chosen direction.
    // Checks if the player requested rooms are valid rooms to jump to,
    // allowing them to jump if so, and staying in the same room if not.
    public Room move(Room start) {
        
        Scanner reader = new Scanner(System.in);
        System.out.println("\nWhat direction would you like to go?");
        System.out.println("N -> North\nE -> East\nS -> South\nW -> West\nC -> Cancel");
        String response;

            while(true) {
                System.out.print("> ");
                response = reader.nextLine().toLowerCase().strip();
                
                if(response.equals("n")) 
                    return tryDirection(start, Direction.NORTH);
                
                else if(response.equals("e")) 
                    return tryDirection(start, Direction.EAST);
                
                else if(response.equals("s")) 
                    return tryDirection(start, Direction.SOUTH);
                
                else if(response.equals("w")) 
                    return tryDirection(start, Direction.WEST);
                
                else if(response.equals("c"))
                    return start;
                
                else 
                    System.out.println("Invalid response. Expecting 'N', 'E', 'S', 'W'.");
            }
    
    }

    // inspectItem() lets the player interact with all the items
    // in the current room. They can choose which item to interact with
    // and this will be further expanded to allow for items to be slotted
    // into their inventory.
    public void inspectItem(Room current) {
        
        Scanner reader = new Scanner(System.in);
        String response;
        int choice;

        while(true) {
            System.out.println("\nInspect an item?");
            System.out.println("C -> Cancel");
            current.listAllItems();
            System.out.print("> ");

            response = reader.nextLine().toLowerCase().strip();
            if(response.equals("c")) return;

            try {
                choice = Integer.parseInt(response)-1;
            } catch(NumberFormatException ex) {
                System.out.println("Invalid Response. Expecting a number from the list.");
                continue;
            }

            if(choice >= current.getAllItemInRoom().size()) {
                System.out.println((choice+1) + " is not a valid choice on the list.");
                continue;
            }
            else if(choice < 0) {
                System.out.println((choice+1) + " is not a valid choice on the list.");
                continue;
            }

            System.out.println();
            System.out.println("Item Description:");
            System.out.println(current.getAllItemInRoom().get(choice).getDescription());
        }

    }

    // inspectRoom() will print a map of the room
    // and mention all the interactable items the
    // player has noticed. 
    public void inspectRoom(Room current) {

        System.out.println("\nROOM MAP: Arrows point to direction of doors in this room.");
        current.printArea();
        System.out.print("You search the room for anything that seems important and find ...\n");
        current.printAllItems();
        return;
    }
}
