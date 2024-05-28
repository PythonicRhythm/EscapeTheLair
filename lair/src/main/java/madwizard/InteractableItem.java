package madwizard;

import java.util.function.Function;

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
