package moves;

import ru.ifmo.se.pokemon.*;

public class Moonblast extends SpecialMove {
    public Moonblast() {
        super(Type.FAIRY, 95, 1);
    }

    @Override
    protected void applyOppEffects(Pokemon def) {
        Effect e = (new Effect()).chance(0.3).attack(0).turns(1);
        e.stat(Stat.SPECIAL_ATTACK, (int) (def.getStat(Stat.SPECIAL_ATTACK) - 6));
        def.setCondition(e);
    }

    @Override
    protected String describe() {
        return "вызывает лунный взрыв";
    }
}
