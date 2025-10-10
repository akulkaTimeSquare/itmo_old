package creatures;

import enums.Mood;
import enums.Status;
import enums.VoiceTone;
import interfaces.Speaking;
import places.Place;

public class Human extends Creature implements Speaking  {
    private final String name;
    private Mood mood;
    private final VoiceTone voice;
    private final boolean isMale;
    private final Status status;

    public Human(String name, boolean isMale, Status status, Mood mood, VoiceTone voice, Place location) {
        super(location);
        if (name == null) name = "";
        this.name = name;
        this.isMale = isMale;
        this.status = status;
        this.mood = mood;
        this.voice = voice;
    }

    public String getName() {
        return name;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public Mood getMood() {
        return mood;
    }

    public VoiceTone getVoice() {
        return voice;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public void speak(String phrase) throws NullPointerException {
        if (phrase == null) throw new NullPointerException("phrase is empty");
        System.out.println(this + " c голосом " + this.getVoice().getName() +
                " и состоянием " + this.getMood().getName() + " сказал" + (this.isMale() ? "" : "а") + ":'" + phrase + "'");
    }

    public void think(String phrase) throws NullPointerException {
        if (phrase == null) throw new NullPointerException("phrase is empty");
        System.out.println(this.getName() + "с состоянием " + this.getMood().getName() + " думает: '" + phrase + "'");
    }

    public void moodAction() {
        String text = this.getName() + " " + switch (this.getMood()) {
            case Happiness -> "катится со смеху";
            case Sadness -> "грустит ";
            case Fury -> "бросается на всё подряд";
            case Wonder -> "струхает";
            case Fine -> "просто стоит";
        };
        System.out.println(text);
    }

    public class humDocument {
        private final int id;

        public humDocument (int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public String getInformation() {
            String text = "Документ гражданина №" + this.getId() + "| ";
            text += Human.this.name;
            text += " | " + (Human.this.isMale ? "Мужчина" : "Девушка");
            text += " | " + Human.this.status.getName();
            return text;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        return this.getName().equals(((Human) obj).getName());
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public int hashCode() {
        return (this.getName() == null ? 0 : this.getName().hashCode());
    }
}

