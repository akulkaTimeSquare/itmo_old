package pokemons;

import moves.ChargeBeam;
import moves.Swagger;
import moves.Thunder;
import ru.ifmo.se.pokemon.Pokemon;

public class Chinchou extends Pokemon {
    public Chinchou(String name, int level) {
        super(name, level);
        this.setStats(75, 38, 38, 56, 56, 67);
        this.addMove(new ChargeBeam());
        this.addMove(new Thunder());
        this.addMove(new Swagger());
    }
}
