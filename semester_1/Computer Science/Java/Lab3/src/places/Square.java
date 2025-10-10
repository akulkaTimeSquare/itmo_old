package places;

import creatures.Creature;

public class Square extends Place {
    private final int beauty;

    public Square(String name, int beauty, Creature... creatures) {
        super(name, creatures);
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
