package moves;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;

public class Thunder extends SpecialMove {
    public Thunder() {
        super(Type.ELECTRIC, 100, 0.7);
    }


    @Override
    protected void applyOppEffects(Pokemon def) {
        if (Math.random() < 0.3) {
            Effect.paralyze(def);
        }
    }

    @Override
    protected String describe() {
        return "выпускает молнию";
    }
}
