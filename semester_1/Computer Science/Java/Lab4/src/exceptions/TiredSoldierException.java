package exceptions;

import creatures.Soldier;

public class TiredSoldierException extends Exception {
    public TiredSoldierException(Soldier soldier) {
        super(soldier.getName() + " падает без головы 0_0");
    }
}
