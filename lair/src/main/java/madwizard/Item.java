package madwizard;

/*
 * The Item class simulates an interactable item spawned in the lair.
 * Items provide a description/lore of the item and items are meant
 * to be interactable in the game. They will be stored in the map/lair
 * or in the player's inventory. 
 */

public class Item {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    Item(String n, String descrip) {
        this.name = n;
        this.description = descrip;
    }

}
