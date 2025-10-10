package moves;

import ru.ifmo.se.pokemon.*;

public class Swagger extends StatusMove {
    public Swagger() {
        super(Type.NORMAL, 0, 0.85);
    }

    @Override
    protected void applySelfEffects(Pokemon att) {
        Effect e = (new Effect()).chance(1).turns(2).attack(0);
        e.stat(Stat.ATTACK, (int) (att.getStat(Stat.ATTACK) + 6));
        att.setCondition(e);
    }

    @Override
    protected String describe() {
        return "очаровывает соперника";
    }
}
