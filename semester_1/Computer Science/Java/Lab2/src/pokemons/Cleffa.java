package pokemons;

import moves.DreamEater;
import moves.Sing;
import ru.ifmo.se.pokemon.Pokemon;

public class Cleffa extends Pokemon {
    public Cleffa(String name, int level) {
        super(name, level);
        this.setStats(50, 25, 28, 45, 55, 18);
        this.addMove(new Sing());
        this.addMove(new DreamEater());
    }
}
