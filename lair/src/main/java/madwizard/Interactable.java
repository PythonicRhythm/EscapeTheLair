package madwizard;

/*
 * The Interactable interface represents the actions that
 * items can take when they are interacted with. Used to 
 * differentiate between interactable, and grabbable items.
 */

public interface Interactable {
    public boolean interactedWith(Room current);
}
