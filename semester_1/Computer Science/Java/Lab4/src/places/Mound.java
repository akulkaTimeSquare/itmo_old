package places;

import creatures.Creature;

public class Mound extends Place {
    private final float height;

    public Mound(String name, int height, int maxCreations, Creature...creatures) {
        super(name, maxCreations, creatures);
        this.height = height;
    }

    public float getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Вершина" + this.getName() + "с высотой " + this.getHeight();
    }
}
