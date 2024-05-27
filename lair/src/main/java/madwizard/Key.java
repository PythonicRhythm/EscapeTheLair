package madwizard;

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
        System.out.println("You've obtained \"" + getName() + "\"?");
    }
}
