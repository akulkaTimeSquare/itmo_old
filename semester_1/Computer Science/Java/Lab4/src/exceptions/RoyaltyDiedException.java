package exceptions;

import creatures.Human;

public class RoyaltyDiedException extends RuntimeException {
    public RoyaltyDiedException(Human human) {
        super("Royalty person " + human.getName() + " died!");
    }
}
