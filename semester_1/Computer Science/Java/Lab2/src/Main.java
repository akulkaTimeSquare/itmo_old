import pokemons.*;
import ru.ifmo.se.pokemon.*;

public class Main {
    public static void main(String[] args) {
        Battle b = new Battle();
        Pokemon maw = new Mawile("Маквин", 30);
        Pokemon chinch = new Chinchou("Чинчау", 20);
        Pokemon lant = new Lanturn("Латунь", 50);
        Pokemon clef = new Cleffa("Трефа", 20);
        Pokemon fairy = new Clefairy("Черва", 40);
        Pokemon fable = new Clefable("Пика", 50);

        b.addAlly(maw);
        b.addAlly(fable);
        b.addAlly(fairy);

        b.addFoe(lant);
        b.addFoe(chinch);
        b.addFoe(clef);

        b.go();
    }
}