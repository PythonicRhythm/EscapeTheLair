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
     *    v    v         v
     *         ^         ^
     *    X << X >< X >< X
     *         v         
     *         ^
     *         X   <--- start 
     * 
     */

    // initializeMap() builds the preset map that is shown up by
    // constructing rooms, items, story, and events. Builds the
    // preset map room by room and item by item.
    static void initializeMap() {

        // Entrance room.
        Room start = new Room("The double doors with his cursed insignia close behind you. An aura of magic envelopes the doors.\n" +
                                "There is no escape. There is no other choice but to find him now.");

        start.addItem(new LoreItem("Wizard's Banner", "The banner that the wizard puts in every destroyed town in Regalia.\n"+
                                   "His insignia is a mage casting a fireball pointed at the viewer\n"+
                                   "with a bloody razor chakram surrounding the mage. Disgusting."));
        
        // Crossroads room.
        Room next = new Room("You entered a putrid charred hall covered in old decayed bodies. The smell is getting worse. On a second look,\n" +
                             "you notice the dead bodies are a small regiment from the armies of Epirus. Must have been their last\n" +
                             "attempt to save their city before the Violent Slumber.");

        next.addItem(new LoreItem("Burnt Map", "The map points to a path to the east and the north. The cartographer left a note about\n" +
                             "a significant item to be found in the east. The rest of the map is destroyed by a fire that occurred in this hall."));
        
        start.setNorth(next);

        next.setSouth(start);

        // Ending room and the key needed for it.
        Key endingRoomKey = new Key("Glowing Key", "Runic Engravings swirl the key and you understand none of them except one. \"Raktha\" which\n"+
                                    "means \"The end and the beginning are one\".", null);
        LockedRoom end = new LockedRoom("You've entered a dank room that appears to be the servant's closet. There is a fresh corpse wearing a \n"+
                                        "tattered robe following your movement. A blueish glow emanates from a rectangular mirror on the wall.\n"+
                                        "You start to feel an alarming sense of anxiety. There is something wrong here.", endingRoomKey);
        end.addItem(new LoreItem("Bloody Sheet", "A sheet you found in the corpse's lap. It states \"What goes up, but never comes down?\"\n"+
                                               "Must be another riddle? The mirror shakes as soon as you finish reading the sheet."));
        end.addItem(new InteractableItem("The Mirror", 
        "Blue sparkling lines swirl and disappear constantly sometimes making complete words.\n"+
        "The mirror notices your gaze and writes \"Require\", then shortly writes \"Answer\".",
        (Room currentRoom) -> {
            Scanner reader = new Scanner(System.in);
        while(true) {
            System.out.println("\nWhat is your answer?");
            System.out.println("C -> Cancel");
            System.out.print("> ");

            String response = reader.nextLine().toLowerCase().strip();
            if(response.equals("age")) {
                System.out.println("\nThe mirror shakes violently until it shatters, sending shards of glass everywhere! You hear a tumble from a corner.\n"+
                                     "The corpse rises and shrieks \"YOU ARE TOO LATE SOLDIER ... MY DEATH HAS ARRIVED BUT MY SOUL REMAINS\". The corpse\n"+
                                     "flies at you but a dextrous roll allowed your escape from it's grasp. The corpse's head turns and whispers\n"+
                                     "\"The way out will open.. may you find me .. may you not. Either way, my presence will remain soldier...\"\n"+
                                     "The western wall collapses and the red darkened sky seeps into the dusty closet. Your adventure here has ended\n"+
                                     "but the wizard remains. Find his soul and exorcise it...");

                System.out.println("\nTHE END! Thanks for playing! I hope the game will have a narrative continuation one day. It was fun!");
                reader.close();
                System.exit(0);
            }
            else if(response.equals("c"))
                return false;
            
            else 
                System.out.println("That did not work I suppose...");
        }
        }));

        endingRoomKey.setDestination(end);
        next.setWest(end);
        end.setEast(next);

        
        Room room = new Room("You enter a dark stained room coated in a red slime. There appears to be damaged furniture stacked\n"+
                                    "all over room. The blockage prevents any access to the room north and west of the room. On further\n"+
                                    "inspection, you notice a disfigured body trapped in a pile of broken tables.");
        room.addItem(new LoreItem("Bloody Ripped Letter", "The letter contains the seal from the royal family in Epirus. The note reads \"\n"+
                                                        "We have gotten word tha.... wizard appears to be suffe... from Lexia. This is\n"+
                                                        "the opportune time to end his tyranny! Send ...... to his lair and finish this."));

        next.setNorth(room);
        room.setSouth(next);

        // Room to the east of the crossroads.
        Room eastOfCross = new Room("You enter a ridiculously long empty hall with what seems to be a door far to the east. Scattered\n"+
                                    "royal banners are present on the walls. You notice these banners are of the families that aligned\n"+
                                    "with the treacherous wizard. Regel, Nimore, Manithem, ... they were all slaughtered.");
        next.setEast(eastOfCross);
        eastOfCross.setWest(next);

        // Locked room that can be opened allowing an alternate path
        // to the winning key item.
        LockedRoom northOfCorner = new LockedRoom("A pitch black hallway with a light protuding from the door to the north.\n"+
                                                  "You observe only darkness... it seems to be sorcery.", null);

        // Room to the southeast part of the map. Contains a riddle that spawns a key
        // which will open the locked room to the north.
        Room easternCorner = new Room("You walk into a very claustrophobic room with stained yellow painted walls. The paint on the\n"+
                                      "walls have been tattered by what appears to be claw marks made by a gigantic beast. You notice\n"+
                                      "an oddly shaped chest positioned perfectly in the center of the room.");
        easternCorner.addItem(new LoreItem("Instruction Sheet", "To open the chest of tricks, solve this riddle!\n"+
                                                                  "\"The more you take, the more you leave behind. What am I?\""));
        easternCorner.addItem(new InteractableItem("Odd Chest", 
        "Theres an inscription on chest itself. \"To open the lock, give the proper answer\".\n"+
        "A game in the middle of this hell? Is this a joke?", 
        (Room currentRoom) -> {
        Scanner reader = new Scanner(System.in);
        while(true) {
            System.out.println("\nWhat is your response?");
            System.out.println("C -> Cancel");
            System.out.print("> ");

            String response = reader.nextLine().toLowerCase().strip();
            if(response.equals("footsteps")) {
                System.out.println("An item has popped out of the chest!");
                Key northernKey = new Key("Chipped Key", "A key that jumped from the chest of tricks.", northOfCorner);
                northOfCorner.setToOpenLock(northernKey);
                currentRoom.addItem(northernKey);
                return true;
            }
            else if(response.equals("c"))
                return false;
            
            else 
                System.out.println("That did not work I suppose...");
        }
        }));

        Room northEasternCorner = new Room("Machinations ting and pang all across the room. Silver and brass boom across the room\n"+
                                           "These machines are one of the wizard's threats to our world. The machine seems to be flowing\n"+
                                           "water to a nearby room.");
        

        northOfCorner.setNorth(northEasternCorner);
        northEasternCorner.setSouth(northOfCorner);
        eastOfCross.setEast(easternCorner);
        easternCorner.setWest(eastOfCross);
        northOfCorner.setSouth(easternCorner);
        easternCorner.setNorth(northOfCorner);


        // Room that gets blocked to the north. South of the 
        // room that contains the key that wins of the game.
        Room blockedRoom = new Room("You enter what appears to be a art gallery filled with easels, brushes, and goblets filled with\n"+
                                    "tainted water. There is a oddly strong smell of fresh paint coming from an easel in the corner.");
        blockedRoom.addItem(new InteractableItem("Odd Easel", "You stare into the painting and you notice it's a perfect recreation of the entrance of the lair.",
                            (Room currentRoom) -> {
                                System.out.println("\nEvent Description:");
                                System.out.println("The painting begins to stutter and emanate a bright blue light. The colors on the easel\n"+
                                                    "begins to swirl like a whirlpool until it all disappears into the middle of the easel.\n"+
                                                    "BOOOM! The door to north shuts loudly and ... slowly shifts to the match the style of the wall.\n"+
                                                    "There is no path north now.");
                                currentRoom.getNorth().setSouth(null);
                                currentRoom.setNorth(null);
                                return true;
                            }));
        
        room.setEast(blockedRoom);
        blockedRoom.setWest(room);
        
        // Room that contains the key that wins the game.
        Room winKeyRoom = new Room("You enter a disgusting rat-infested kitchen. A smell of rot permeates through the air and impairs your senses.\n"+
                                   "Butchered carcasses litter the floor and wooden pottery linger the counters. Your eye catches a glow from a nearby carcass.");
        winKeyRoom.addItem(endingRoomKey);
        blockedRoom.setNorth(winKeyRoom);
        winKeyRoom.setSouth(blockedRoom);
        northEasternCorner.setWest(winKeyRoom);
        winKeyRoom.setEast(northEasternCorner);

        // Add all rooms to the list of rooms.
        map.add(start);
        map.add(winKeyRoom);
        map.add(northEasternCorner);
        map.add(easternCorner);
        map.add(northOfCorner);
        map.add(eastOfCross);
        map.add(end);
        map.add(next);
        map.add(room);
        map.add(blockedRoom);
        
    }

    public static void run() {

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
