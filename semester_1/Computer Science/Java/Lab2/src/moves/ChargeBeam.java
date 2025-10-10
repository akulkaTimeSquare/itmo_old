package moves;

import ru.ifmo.se.pokemon.*;

public class ChargeBeam extends SpecialMove {
    public ChargeBeam() {
        super(Type.ELECTRIC, 50, 0.9);
    }

    @Override
    protected void applySelfEffects(Pokemon att) {
        Effect e = (new Effect()).chance(0.7).turns(1).attack(0);
        e.stat(Stat.SPECIAL_ATTACK, (int) (att.getStat(Stat.SPECIAL_ATTACK) + 6));
        att.setCondition(e);
    }

    @Override
    protected String describe() {
        return "выпускает энергетический луч";
    }
}
