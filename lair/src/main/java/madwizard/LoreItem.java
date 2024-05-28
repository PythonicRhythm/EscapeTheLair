package madwizard;

/*
 * The LoreItem class represents a grabbable item that
 * provides hints or lore about the game and its setting.
 * An instance of this class can be stored in the player's
 * inventory.
 */

public class LoreItem extends Item implements Grabbable {
    
    LoreItem(String n, String descrip) {
        super(n, descrip);
    }

    @Override
    public void grabbed() {
        System.out.println("\nYou've obtained \"" + getName() + "\"?");
    }
}
