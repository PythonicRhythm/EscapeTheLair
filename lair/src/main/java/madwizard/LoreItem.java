package madwizard;

public class LoreItem extends Item implements Grabbable {
    
    LoreItem(String n, String descrip) {
        super(n, descrip);
    }

    @Override
    public void grabbed() {
        System.out.println("\nYou've obtained \"" + getName() + "\"?");
    }
}
