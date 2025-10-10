package creatures;

import enums.Mood;
import enums.Status;
import enums.VoiceTone;
import places.Place;

public class FlagMan extends Human {
    static private float flagSquare = 5;
    static private int numberOfStars = 3;

    public FlagMan(String name, boolean isMale, Mood mood, VoiceTone voice, Place location) {
        super(name, isMale, Status.Standard, mood, voice, location);
    }

    public void moveFlag (Flag flag, int delta) {
        if (flag == null) System.out.println("Пытается безуспешно поднять пустоту");
        System.out.println(flag.move(delta) + " с помощью " + this.getName());
    }

    static public class Flag {
        private int currentHeight = 0;
        private final int maxHeight;

        public Flag(int maxHeight, int number) {
            this.maxHeight = maxHeight;
        }

        public int getMaxHeight() {
            return maxHeight;
        }

        public void setCurrentHeight(int currentHeight) {
            this.currentHeight = currentHeight;
        }

        public int getCurrentHeight() {
            return currentHeight;
        }

        public int getNumberOfStars() {
            return numberOfStars;
        }

        private String move(int delta) {
            int temp = this.getCurrentHeight() + delta;
            if (temp >= 0 && temp <= this.getMaxHeight()) this.setCurrentHeight(temp);
            return "Флаг " + " с площадью " + FlagMan.flagSquare + " и количеством звёзд " + FlagMan.numberOfStars + " был " +
                    (delta >= 0 ? " поднят " : "опущен ") + "на " + delta +
                    (this.getMaxHeight() == this.getCurrentHeight() ? " (максимальная высота)" : "");
        }

    }
}
