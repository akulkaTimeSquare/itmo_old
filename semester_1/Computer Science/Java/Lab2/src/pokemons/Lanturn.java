package pokemons;

import moves.EerieImpulse;

public class Lanturn extends Chinchou {
    public Lanturn(String name, int level) {
        super(name, level);
        this.setStats(125, 58, 58, 76, 76, 67);
        this.addMove(new EerieImpulse());
    }
}
