package madwizard;

import java.util.function.Function;

/*
 * Interactable represents an Item that is specifically
 * meant to be interacted with such as a lever or an item
 * that prompts a riddle. A function is saved upon creation
 * that will be called upon an interaction with the item.
 * An interactable item cannot be grabbed and stored in the
 * player's pouch.
 */

public class InteractableItem extends Item implements Interactable{
    
    private Function<Room, Object> onInteraction;

    InteractableItem(String n, String descrip, Function<Room, Object> onInteract) {
        super(n, descrip);
        onInteraction = onInteract;
    }

    @Override
    public boolean interactedWith(Room current) {
        return (boolean) onInteraction.apply(current);
    }
}
