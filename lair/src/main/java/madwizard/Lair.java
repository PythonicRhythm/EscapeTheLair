package madwizard;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Project: (Single Dev): Escape the Mad Wizard's Lair
 *  Story Development:
 *      Create a storyline where the player is trapped in a mad wizard's lair and must find a way to escape.
 *      Develop a series of rooms, challenges, and encounters that the player must navigate through.
 *      Write descriptive text for each room, including details about the surroundings, objects, and any clues or hints.
 *      Examples:
 *          https://youtu.be/jAf1I1UWo5Q?t=103
 *          https://www.lisperati.com/data.html
 *  Game Mechanics:
 *      Implement a system where players can make choices and perform actions in each room.
 *      Choices can include examining objects, interacting with elements, or moving to different rooms.
 *      Each choice should have consequences and lead to different outcomes or paths in the game.
 *      Allow players to input their choices using the keyboard
 *  Inventory System:
 *      Implement an inventory system where players can collect and use items found in the wizard's lair.
 *      Items can be used to solve puzzles, overcome obstacles, or gain access to new areas.
 *      Allow players to combine items to create new objects or solutions.
 *  Puzzle Solving:
 *      Include a variety of puzzles and challenges throughout the game that players must solve to progress.
 *      Puzzles can include riddles, logic problems, pattern recognition, or magical elements.
 *      Solving puzzles should reward players with items, clues, or access to new rooms.
 *  Game Progression:
 *      Design a logical progression through the wizard's lair, with each room or area leading to the next.
 *      Include multiple paths or optional areas that players can explore.
 *      Implement a system to track the player's progress and determine when they have successfully escaped the lair.
 */
public class Lair 
{
    private static List<Room> map = new LinkedList<>(); // doubly linked list array of room that represents the map.

     // Enum that represents the cardinal directions
     // for traveling between rooms.
    public enum Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }

    /*
     *           MAP/LAIR LAYOUT
     * 
     *              X >< X
     *   end        v    v
     *    |         ^    ^
     *    |    X >< X    X
     *    v    v    v    v
     *         ^    ^    ^
     *    X << X >< X >< X
     *         v         
     *         ^
     *         X   <--- start 
     * 
     */

    // initializeMap() builds the preset map that is shown up by
    // constructing rooms, items, story, and events.
    static void initializeMap() {
        Room current = new Room("The double doors with his cursed insignia close behind you. An aura of magic envelopes the doors.\n" +
                                "There is no escape. There is no other choice but to find him now.");

        current.addItem(new Item("Wizard's Banner", "The banner that the wizard puts in every destroyed town in Regalia.\n"+
                                   "His insignia is a mage casting a fireball pointed at the viewer\n"+
                                   "with a bloody razor chakram surrounding the mage. Disgusting."));
        
        Room next = new Room("You entered a putrid charred hall covered in old decayed bodies. The smell is getting worse. On a second look,\n" +
                             "you notice the dead bodies are a small regiment from the armies of Epirus. Must have been their last\n" +
                             "attempt to save their city before the Violent Slumber.");

        next.addItem(new Item("Burnt Map", "The map points to a path to the east and the north. The cartographer left a note about\n" +
                             "a significant item to be found in the east. The rest of the map is destroyed by a fire that occurred in this hall."));
        
        current.setNorth(next);
        map.add(current);

        next.setSouth(current);

        {
            Room room = new Room("You enter a dark stained room coated in a red slime. There appears to be damaged furniture stacked\n"+
                                      "all over room. The blockage prevents any access to the room north and west of the room. On further\n"+
                                      "inspection, you notice a disfigured body trapped in a pile of broken tables.");
            room.addItem(new Item("Bloody Ripped Letter", "The letter contains the seal from the royal family in Epirus. The note reads \"\n"+
                                                            "We have gotten word tha.... wizard appears to be suffe... from Lexia. This is\n"+
                                                            "the opportune time to end his tyranny! Send ...... to his lair and finish this."));

            next.setNorth(room);
            room.setSouth(next);

            Room blockedRoom = new Room("You enter what appears to be a art gallery filled with easels, brushes, and goblets filled with\n"+
                                        "tainted water. There is a oddly strong smell of fresh paint coming from an easel in the corner.");
            blockedRoom.addItem(new Item("Odd Easel", "You stare into the painting and you notice it's a perfect recreation of the entrance of the lair.\n"+
                                                        "The painting begins to stutter and emanate a bright blue light. The colors on the easel begins to\n"+
                                                        "swirl like a whirlpool and the door to the north shuts."));
            
            room.setEast(blockedRoom);
            blockedRoom.setWest(room);
        }

        next.setNorth(next);
                
        map.add(next);
        
    }

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        System.out.println("\nYou have entered the lair of the mad wizard who has caused pain and suffering\n" +
                           "throughout generations. A sense of dread begins to creep over you slowly as the\n" +
                           "smell of death overcomes you. You were sent on a mission to finally slay him\n" +
                           "but can you? Your journey ends here ... one way or another. Find him.");
        
        initializeMap();
        Player user = new Player();
        Room current = map.get(0);

        while(true) {
            System.out.println("\nRoom Description:");
            System.out.println(current.getDescription());
            System.out.println("\nI -> Inspect Room\nM -> Move\nP -> Pouch\nQ -> Quit");
            System.out.print("> ");

            String response = reader.nextLine().toLowerCase().strip();
            if(response.equals("i")) {
                user.inspectRoom(current);

                if(current.getAllItemInRoom().size() > 0) {
                    user.inspectItemsInRoom(current);

                }
            }
            else if(response.equals("m")) {
                current = user.move(current);
            }
            else if(response.equals("q")){
                System.out.println("\nFrom every wall in the room, you hear high-pitched chortling. \"The coward's way out, 'Hero'? I did not expect that.\"\n"+
                                     "You slowly turn around and notice a wooden staff pointed directly at your skull emitting heat from a red glowing orb at the tip.\n"+
                                     "\"Good riddance, ...\"\n");
                System.exit(0);
            }
            else if(response.equals("p")) {
                user.inspectInventory(current);
            }
            else 
                System.out.println("Invalid response. Expecting 'I', 'M', 'Q'.");
        }
        
    }
}
