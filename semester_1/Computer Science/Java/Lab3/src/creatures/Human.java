package creatures;

import places.Place;

public class Human extends Creature implements Speaking {
    private final String name;
    private Mood mood;
    private final VoiceTone voice;
    private final boolean isMale;

    public Human(String name, boolean isMale, Mood mood, VoiceTone voice, Place location) {
        super(location);
        if (name == null) name = "";
        this.name = name;
        this.isMale = isMale;
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

    @Override
    public String speak(String phrase) {
        return this + " c голосом " + this.getVoice().getName() +
                " и состоянием " + this.getMood().getName() + " сказал" + (this.isMale() ? "" : "а") + ":'" + phrase + "'";
    }

    public String moodAction() {
        return this.getName() + " " + switch (this.getMood()) {
            case Happiness -> "катится со смеху";
            case Sadness -> "грустит ";
            case Fury -> "бросается на всё подряд";
            case Wonder -> "порядком струхает";
            case Fine -> "просто стоит";
        };
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

