package madwizard;

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
