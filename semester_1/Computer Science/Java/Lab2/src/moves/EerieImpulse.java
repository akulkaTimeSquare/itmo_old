package moves;

import ru.ifmo.se.pokemon.*;

public class EerieImpulse extends StatusMove {
    public EerieImpulse() {
        super(Type.ELECTRIC, 0, 1);
    }

    @Override
    public void applyOppEffects(Pokemon def) {
        Effect e = (new Effect()).chance(1).turns(2).attack(0);
        e.stat(Stat.SPECIAL_ATTACK, (int) (def.getStat(Stat.SPECIAL_ATTACK) - 6));
        def.setCondition(e);
    }

    @Override
    protected String describe() {
        return "накладывает страх";
    }
}
