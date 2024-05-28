package madwizard;

/*
 * The Key class represents a key that unlocks a room
 * in the lair. An instance of a key stores a 'destination'
 * room that the key will unlock. The key can be grabbed
 * and stored in the pouch of the player.
 */

public class Key extends Item implements Grabbable {

    Room destination;

    Key(String n, String descrip, Room lock) {
        super(n, descrip);
        this.destination = lock;
    }

    public Room getDestination() {
        return destination;
    }

    public void setDestination(Room destination) {
        this.destination = destination;
    }

    @Override
    public void grabbed() {
        System.out.println("\nYou've obtained \"" + getName() + "\"?");
    }
}
