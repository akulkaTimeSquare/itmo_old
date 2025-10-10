package moves;

import ru.ifmo.se.pokemon.*;

public class StoneEdge extends PhysicalMove {
    public StoneEdge() {
        super(Type.ROCK, 100, 0.8);
    }

    @Override
    protected double calcCriticalHit(Pokemon att, Pokemon def) {
        Effect e = (new Effect()).chance(1).turns(0);
        e.stat(Stat.SPEED, (int) (att.getStat(Stat.SPEED) * 3));
        att.setCondition(e);
        return super.calcCriticalHit(att, def);
    }

    @Override
    protected String describe () {
        return "кидает валун";
    }
}
