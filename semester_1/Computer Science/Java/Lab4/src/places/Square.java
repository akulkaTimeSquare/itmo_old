package places;

import creatures.Creature;

public class Square extends Place {
    private final int beauty;

    public Square(String name, int beauty, int maxCreatures, Creature... creatures) {
        super(name, maxCreatures, creatures);
        this.beauty = Math.max(0, Math.min(beauty, 100));
    }

    public int getBeauty() {
        return beauty;
    }

    @Override
    public String toString() {
        return this.getName() + " площадь (красота - " + getBeauty() + ")";
    }
}
