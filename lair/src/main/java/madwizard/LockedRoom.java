package madwizard;

public class LockedRoom extends Room {
    
    Key toOpenLock;

    LockedRoom(String descrip, Key keyForLock) {
        super(descrip);
        this.toOpenLock = keyForLock;
    }

    public Key getToOpenLock() {
        return toOpenLock;
    }
}
