package madwizard;

import java.util.ArrayList;
import java.util.Scanner;

import madwizard.Lair.Direction;

public class Player {

    ArrayList<Item> inventory = new ArrayList<>();

    // Add the incoming Item into the player's inventory
    public void addItemIntoInventory(Item toBeAdded) {
        inventory.add(toBeAdded);
    }

    // List all the items in the player's inventory.
    // Ex:  1 -> Wizard's Banner
    //      2 -> Burnt Map            
    public void listAllItemsInInventory() {
        for(int i = 0; i < inventory.size(); i++) {
            System.out.println((i+1) + " -> " + inventory.get(i).getName());
        }
    }

    // Remove the item in inventory with the incoming
    // index. Check for valid indices.
    public void removeItem(int index) {
        if(index >= inventory.size() || index < 0) {
            System.out.println("Invalid index. No negative values or values greater than the size of inventory.");
        }
        inventory.remove(index);
    }

    public Room tryToUnlockRoom(LockedRoom roomToUnlock) {
        for(Item i: inventory) {
            if(i instanceof Key) {
                Key possibleKeyToDoor = ((Key) i);
                if(possibleKeyToDoor.getDestination() == roomToUnlock) {
                    System.out.println("Used \""+i.getName()+"\" to unlock the door!");
                    return new Room(roomToUnlock);
                } 
            }
        }

        return null;
    }
    
    // tryDirection() trys out a direction given by the player.
    // If its a valid move, return the room that the user requested,
    // and print a message mentioning the player's movement.
    // else it returns the room the player is currently in and prints
    // a message mentioning either a missing door or locked room.
    public Room tryDirection(Room start, Direction direction) {
        System.out.println();
        switch(direction) {
            case NORTH:
                if(start.getNorth() == null) { 
                    System.out.println("No access to that direction.");
                    return start;
                }
                else if(start.getNorth() instanceof LockedRoom) {
                    if(tryToUnlockRoom((LockedRoom) start.getNorth()) == null) {
                        System.out.println("The room is locked! Maybe there is a key?");
                        return start;
                    }
                    else {
                        System.out.println("You proceed to the now unlocked northern room...");
                        return start.getNorth();
                    }
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
                else if(start.getEast() instanceof LockedRoom) {
                    if(tryToUnlockRoom((LockedRoom) start.getEast()) == null) {
                        System.out.println("The room is locked! Maybe there is a key?");
                        return start;
                    }
                    else {
                        System.out.println("You proceed to the now unlocked eastern room...");
                        return start.getEast();
                    }
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
                else if(start.getSouth() instanceof LockedRoom) {
                    if(tryToUnlockRoom((LockedRoom) start.getSouth()) == null) {
                        System.out.println("The room is locked! Maybe there is a key?");
                        return start;
                    }
                    else {
                        System.out.println("You proceed to the now unlocked southern room...");
                        return start.getSouth();
                    }
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
                else if(start.getWest() instanceof LockedRoom) {
                    if(tryToUnlockRoom((LockedRoom) start.getWest()) == null) {
                        System.out.println("The room is locked! Maybe there is a key?");
                        return start;
                    }
                    else {
                        System.out.println("You proceed to the now unlocked west room...");
                        return start.getWest();
                    }
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

    // inspectInventory() lets the player interact with all the items
    // in their inventory. They can also drop items into the room they
    // are currently positioned.
    public void inspectInventory(Room current) {
        Scanner reader = new Scanner(System.in);
        String response;
        int choice;

        if(inventory.size() < 1) {
            System.out.println("Nothing in your pouch.");
            return;
        }

        while(true) {
            System.out.println("\nInspect an item in your pouch?");
            listAllItemsInInventory();
            System.out.println("C -> Cancel");
            System.out.print("> ");

            response = reader.nextLine().toLowerCase().strip();
            if(response.equals("c")) return;

            try {
                choice = Integer.parseInt(response)-1;
            } catch(NumberFormatException ex) {
                System.out.println("Invalid Response. Expecting a number from the list.");
                continue;
            }

            if(choice >= inventory.size()) {
                System.out.println((choice+1) + " is not a valid choice on the list.");
                continue;
            }
            else if(choice < 0) {
                System.out.println((choice+1) + " is not a valid choice on the list.");
                continue;
            }

            System.out.println();
            System.out.println("Item Description:");
            System.out.println(inventory.get(choice).getDescription());
            dropItem(choice, current);

            if(inventory.size() < 1) return;
        }
    }

    // dropItem() asks the user if they would like to drop the
    // highlighted item in the room they are currently positioned.
    // If so, item is transferred to the room.
    public void dropItem(int index, Room current) {

        Scanner reader = new Scanner(System.in);
        String response;

        while(true) {
            
            System.out.println("\nDrop \""+inventory.get(index).getName()+"\" in this room?");
            System.out.println("D -> Drop");
            System.out.println("C -> Cancel");
            System.out.print("> ");

            response = reader.nextLine().toLowerCase().strip();
            if(response.equals("c")) return;
            else if(response.equals("d")) {
                System.out.println();
                System.out.println("You've dropped \""+inventory.get(index).getName()+"\"?");
                current.addItem(inventory.get(index));
                removeItem(index);
                return;
            }
            else
                System.out.println("Invalid Response. Expecting 'C' or 'G'.");
        
        }

    }

    // inspectItemsInRoom() lets the player interact with all the items
    // in the current room. They can choose which item to interact with
    // and they can also add items into their inventory.
    public void inspectItemsInRoom(Room current) {
        
        Scanner reader = new Scanner(System.in);
        String response;
        int choice;

        while(true) {
            System.out.println("\nInspect an item in the room?");
            current.listAllItems();
            System.out.println("C -> Cancel");
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
            Item currItem = current.getAllItemInRoom().get(choice);
            System.out.println(currItem.getDescription());
            if(currItem instanceof Grabbable) {
                grabItem(choice, current);
            }
            else if(currItem instanceof Interactable) {
                interactWithItem(choice, current);
            }

            if(current.getAllItemInRoom().size() < 1) return;
        }

    }

    // interactWithItem() asks if the player would like to 
    // interact with the highlighted item in the current room.
    // If so, the item causes an event in the room.
    public void interactWithItem(int index, Room current) {

        Scanner reader = new Scanner(System.in);
        String response;
        Item currentItem = current.getAllItemInRoom().get(index);

        while(true) {
            
            System.out.println("\nInteract with \""+currentItem.getName()+"\"?");
            System.out.println("I -> Interact");
            System.out.println("C -> Cancel");
            System.out.print("> ");

            response = reader.nextLine().toLowerCase().strip();
            if(response.equals("c")) return;
            else if(response.equals("i")) {
                if(((Interactable) currentItem).interactedWith(current))
                    current.removeItem(index);
                return;
            }
            else
                System.out.println("Invalid Response. Expecting 'C' or 'G'.");
        
        }
        
    }

    // grabItem() asks if the player would like to grab the
    // highlighted item in the current room.
    // If so, the item leaves the room and transfers to
    // the player's inventory/pouch.
    public void grabItem(int index, Room current) {

        Scanner reader = new Scanner(System.in);
        String response;
        Item currentItem = current.getAllItemInRoom().get(index);

        while(true) {
            
            System.out.println("\nGrab \""+currentItem.getName()+"\"?");
            System.out.println("G -> Grab");
            System.out.println("C -> Cancel");
            System.out.print("> ");

            response = reader.nextLine().toLowerCase().strip();
            if(response.equals("c")) return;
            else if(response.equals("g")) {
                ((Grabbable) currentItem).grabbed();
                addItemIntoInventory(currentItem);
                current.removeItem(index);
                return;
            }
            else
                System.out.println("Invalid Response. Expecting 'C' or 'G'.");
        
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
