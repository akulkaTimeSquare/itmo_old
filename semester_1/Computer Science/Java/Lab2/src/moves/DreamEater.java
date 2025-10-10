package moves;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Status;
import ru.ifmo.se.pokemon.Type;

public class DreamEater extends SpecialMove {
    public DreamEater() {
        super(Type.PSYCHIC, 100, 100);
    }

    @Override
    protected void applyOppDamage(Pokemon def, double v) {
        if (def.getCondition() == Status.SLEEP) { super.applyOppDamage(def, v);}
        else {super.applyOppDamage(def, 0); }
    }

    @Override
    protected void applySelfDamage(Pokemon att, double v) {
        super.applySelfDamage(att,  (int) (- v / 2));
    }

    @Override
    protected String describe() {
        return "обрушивает на врага его худшие кошмары";
    }
}
