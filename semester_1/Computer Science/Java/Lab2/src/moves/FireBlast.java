package moves;

import ru.ifmo.se.pokemon.*;

public class FireBlast extends SpecialMove {
    public FireBlast() {
        super(Type.FIRE, 110, 85);
    }

    @Override
    public void applyOppEffects(Pokemon def) {
        if (Math.random() < 0.1) { Effect.burn(def); }
    }

    @Override
    protected String describe() {
        return "вызвает метеорит c небес";
    }
}
