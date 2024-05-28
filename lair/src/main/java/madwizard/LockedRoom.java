package madwizard;

/*
 * The LockedRoom class represents a locked room in the lair.
 * An instance of a LockedRoom stores the specific key that can
 * be used to unlock the room.
 */

public class LockedRoom extends Room {
    
    Key toOpenLock;

    LockedRoom(String descrip, Key keyForLock) {
        super(descrip);
        this.toOpenLock = keyForLock;
    }

    public Key getToOpenLock() {
        return toOpenLock;
    }

    public void setToOpenLock(Key toOpenLock) {
        this.toOpenLock = toOpenLock;
    }
}
