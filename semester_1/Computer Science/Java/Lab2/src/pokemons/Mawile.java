package pokemons;

import moves.ChargeBeam;
import moves.Facade;
import moves.FireBlast;
import moves.StoneEdge;
import ru.ifmo.se.pokemon.Pokemon;

public class Mawile extends Pokemon {
    public Mawile(String name, int level) {
        super(name, level);
        this.setStats(50, 85, 85, 55, 55, 50);
        this.addMove(new FireBlast());
        this.addMove(new ChargeBeam());
        this.addMove(new Facade());
        this.addMove(new StoneEdge());
    }
}
