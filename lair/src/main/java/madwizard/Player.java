package madwizard;

import madwizard.Lair.Direction;

public class Player {
    
    public Room move(Room start, Direction direction) {
        
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

    public void interact() {
        // TODO Auto-generated method stub
        return;
    }

    public void inspectRoom(Room current) {
        // TODO Auto-generated method stub
        current.printArea();
        System.out.print("You search the room for anything that seems important and find ... ");
        current.printAllItem();
        return;
    }
}
