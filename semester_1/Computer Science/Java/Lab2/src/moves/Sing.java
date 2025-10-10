package moves;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class Sing extends StatusMove {
    public Sing() {
        super(Type.NORMAL, 0, 0.55);
    }

    @Override
    protected void applyOppEffects(Pokemon def) {
        Effect.sleep(def);
    }

    @Override
    protected String describe() {
        return "усыпляет врага";
    }
}
