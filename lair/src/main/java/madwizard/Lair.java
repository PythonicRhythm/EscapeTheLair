package madwizard;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Lair 
{
    private static List<Room> map = new LinkedList<>();

    /*
     *           MAP/LAIR LAYOUT
     * 
     *                        X >< X
     *                        v    v
     *                        ^    ^
     *         X >< X    X >< X    X
     *         v    v    v    v    v
     *         ^    ^    ^    ^    ^
     *         X    X >< X >< X >< X
     *         v         v         
     *         ^         ^
     *         X >< X    X   <--- start 
     *              ^
     *              |
     *             end
     */

     public enum Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST
     }

    static void initializeMap() {
        Room current = new Room("The double doors with his cursed insignia close behind you. An aura of magic envelopes the doors.\n" +
                                "There is no escape. There is no other choice but to find him now.");
        current.addItem(new Item("Wizard's Banner", "The banner that the wizard puts in every destroyed town in .\n"+
                                   "His insignia is a mage casting a fireball pointed at the viewer\n"+
                                   "with a bloody razor chakram surrounding the mage. Disgusting."));
        
        Room next = new Room("You entered a putrid charred hall covered in old decayed bodies. The smell is getting worse. On a second look,\n" +
                             "you notice the dead bodies are a small regiment from the armies of Epirus. Must have been their last\n" +
                             "attempt to save their city before the Violent Slumber.");
        next.addItem(new Item("Burnt Map", "The map points to a path to the east and the north. The cartographer left a note about\n" +
                             "a significant item to be found in the east. The rest of the map is destroyed by a fire that occurred in this hall."));
        
        current.setNorth(next);
        next.setSouth(current);
        map.add(current);
        map.add(next);
        
    }

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        System.out.println("\nYou have entered the lair of the mad wizard who has caused pain and suffering\n" +
                           "throughout generations. A sense of dread begins to creep over you slowly as the\n" +
                           "smell of death overcomes you. You were sent on a mission to finally slay him\n" +
                           "but can you? Your journey ends here ... one way or another. Find him.\n");
        
        initializeMap();
        Player user = new Player();
        Room current = map.get(0);

        while(true) {
            System.out.println("\n" + current.getDescription());
            System.out.println("\nI -> Inspect\nM -> Move\nQ -> Quit");
            System.out.print("> ");

            String response = reader.nextLine().toLowerCase().strip();
            if(response.equals("i")) {
                user.inspectRoom(current);
            }
            else if(response.equals("m")) {
                System.out.println("\nN -> North\nE -> East\nS -> South\nW -> West");

                while(true) {
                    System.out.print("> ");
                    response = reader.nextLine().toLowerCase().strip();
                    if(response.equals("n")) {
                        current = user.move(current, Direction.NORTH);
                        break;
                    }
                    else if(response.equals("e")) {
                        current = user.move(current, Direction.EAST);
                        break;
                    }
                    else if(response.equals("s")) {
                        current = user.move(current, Direction.SOUTH);
                        break;
                    }
                    else if(response.equals("w")) {
                        current = user.move(current, Direction.WEST);
                        break;
                    }
                    else 
                        System.out.println("Invalid response. Expecting 'N', 'E', 'S', 'W'.");
                }
            }
            else if(response.equals("q")){
                System.out.println("\nFrom every wall in the room, you hear high-pitched chortling. \"The coward's way out, 'Hero'? I did not expect that.\"\n"+
                                     "You slowly turn around and notice a wooden staff pointed directly at your skull in front of a dark smile. \"Good riddance, ...\"\n");
                System.exit(0);
            }
            else 
                System.out.println("Invalid response. Expecting 'I', 'M', 'Q'.");
        }
        
    }
}
