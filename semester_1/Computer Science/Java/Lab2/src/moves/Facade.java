package moves;

import ru.ifmo.se.pokemon.*;

public class Facade extends PhysicalMove {
    public Facade() {
        super(Type.NORMAL, 70, 1);
    }

    protected void applyOppDamage(Pokemon def, double v) {
        Status condition = def.getCondition();
        if ((condition == Status.BURN) || (condition == Status.POISON) ||
                (condition == Status.PARALYZE)) {super.applyOppDamage(def, v * 2); }
        else { super.applyOppDamage(def, v); }
    }

    protected String describe () {
        return "спускает град камней";
    }
}
