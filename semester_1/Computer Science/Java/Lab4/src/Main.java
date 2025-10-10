import creatures.*;
import creatures.animals.Flamingo;
import creatures.animals.Hedgehog;
import enums.Mood;
import enums.Status;
import enums.VoiceTone;
import exceptions.RoyaltyDiedException;
import exceptions.TiredSoldierException;
import places.*;

public class Main {
    public static void main(String[] args) {
        Status[] statuses = new Status[]{Status.Worse, Status.Standard, Status.Special, Status.Great, Status.Royalty};
        String[] names = new String[]{"Рони", "Том", "Джек", "Ануин", "Эдриан", "Боб", "Стив", "Гюнтер", "Никол", "Элджернон"};

        Plain croquetPlain = new Plain("Крокетное", 100, 75, 50);
        Square royalSquare = new Square("Королевская", 9, 20);

        Hedgehog aliceHedgehog = new Hedgehog(croquetPlain);
        Flamingo aliceFlamingo = new Flamingo(croquetPlain);
        Player alice = new Player("Алиса", false, Status.Special, Mood.Fine, VoiceTone.Optimistic,
                aliceFlamingo, aliceHedgehog, croquetPlain) {
            @Override
            public void hitHedgehog(){
                if (this.getOwnFlamingo().getTakenStatus() || this.getOwnFlamingo().getNeckStatus()) {
                    System.out.println(this.getName() + " пытается ударить фламинго, но не получается");
                    this.getOwnFlamingo().lookBehind();
                }
                else {
                    this.takeFlamingo();
                    this.straightFlamingoNeck();
                    this.hitHedgehog();
                }
            }
        };

        Human queen = new Human("Королева",false, Status.Great, Mood.Fine, VoiceTone.Squeak, royalSquare) {
            private int furriness = 0;
            private final int maxFurriness = 100;
            @Override
            public void speak(String phrase) throws NullPointerException, RoyaltyDiedException {
                super.speak(phrase);
                this.furriness += 10;
                if (this.furriness == this.maxFurriness) {
                    throw new RoyaltyDiedException(this);
                }
            }
        };

        Soldier[] soldiers = new Soldier[] {new Soldier(names[(int) (Math.random() * 9)],
                                true, Mood.Fine, VoiceTone.Rough, croquetPlain, royalSquare),
                new Soldier(names[(int) (Math.random() * 9)],
                                true, Mood.Sadness, VoiceTone.Serious, croquetPlain, royalSquare),
                new Soldier(names[(int) (Math.random() * 9)],
                                true, Mood.Wonder, VoiceTone.Squeak, croquetPlain, royalSquare)};

        Soldier[] gateSoldiers = new Soldier[] {new Soldier("Ануин",
                                true, Mood.Fine, VoiceTone.Rough, croquetPlain, royalSquare),
                new Soldier(names[(int) (Math.random() * 9)],
                                true, Mood.Sadness, VoiceTone.Serious, croquetPlain, royalSquare),
                new Soldier(names[(int) (Math.random() * 9)],
                                true, Mood.Wonder, VoiceTone.Squeak, croquetPlain, royalSquare),
                new Soldier(names[(int) (Math.random() * 9)],
                                true, Mood.Happiness, VoiceTone.Optimistic, croquetPlain, royalSquare){
                    @Override
                    public void takeGatePose() {
                        System.out.println(this.getName() + " изо всех сил пытается встать в стойку ворот и падает на землю...");
                    }
                }};

        Player[] players = new Player[10];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player("Игрок#" + i, (Math.random() > 0.5), statuses[(int) (Math.random() * 4)],
                    Mood.Fury, VoiceTone.Standard, new Flamingo(croquetPlain), new Hedgehog(croquetPlain), croquetPlain);
        }
        FlagMan.Flag[] flags = new FlagMan.Flag[]{new FlagMan.Flag(100, 1),
                new FlagMan.Flag(150, 2), new FlagMan.Flag(100, 3)};
        FlagMan flagMan = new FlagMan("Джулия", false, Mood.Happiness, VoiceTone.Optimistic, royalSquare);

        System.out.println("Игра начинается!");
        for (FlagMan.Flag flag: flags) {
            flagMan.moveFlag(flag, flag.getMaxHeight());
        }
        for (Soldier soldier: gateSoldiers) {
            try {
                soldier.takeGatePose();
            }
            catch (TiredSoldierException exception) {
                soldier.move();

            }
        }
        alice.takeFlamingo();
        System.out.println();
        for (Soldier soldier : soldiers) {
            soldier.move();
        }
        System.out.println();
        alice.straightFlamingoNeck();
        alice.setHedgehogDirection(gateSoldiers[(int) (Math.random() * 3)]);
        alice.hitHedgehog();
        alice.moodAction();
        alice.straightFlamingoNeck();
        alice.getOwnHedgehog().move();
        alice.setMood(Mood.Sadness);
        alice.moodAction();
        System.out.println();

        class Judge extends Human{
            public Judge(String name, boolean isMale, Status status, Mood mood, VoiceTone voice, Place location) {
                super(name, isMale, status, mood, voice, location);
            }
            public void checkHit(Player player) {
                String text = switch (player.getStatus()) {
                    case Worse -> "Худший в мире удар, ты как вообще сюда попал?!";
                    case Standard -> "Хороший удар...";
                    case Special -> "Не зря вас называют особенным.";
                    case Great -> "Так держать, вы играете всё лучше и лучше!";
                    case Royalty -> "Вы лучше всех, Ваша Светлость, ЛУЧШЕ ВСЕХ!";
                };
                this.speak(text);
            }
            public void checkDocuments(Player player) {
                Human.humDocument document = player.new humDocument(player.getName().hashCode());
                System.out.println(this.getName() + " проверяет документы " + player.getName());
                System.out.println(document.getInformation());
                String text = (player.getStatus() == Status.Worse ? "Надо будет вас проверить..." : "Всё хорошо, спасибо");
                this.speak(text);
            }
        }
        Judge judge = new Judge(names[(int) (Math.random() * 9)], true, Status.Standard,
                Mood.Fine, VoiceTone.Standard, croquetPlain);
        System.out.println("В одно и то же время:");
        queen.setMood(Mood.Fury);
        for (Player player: players) {
            player.takeFlamingo();
            player.straightFlamingoNeck();
            player.setHedgehogDirection(gateSoldiers[(int) (Math.random() * 3)]);
            player.hitHedgehog();
            judge.checkHit(player);
            judge.checkDocuments(player);
            if (Math.random() < 0.3) {
                if (player.isMale()) {
                    queen.speak("Отрубить ему голову");
                }
                else {
                    queen.speak("Отрубить ей голову");
                }
            }
        }

        System.out.println();
        System.out.println("Через некоторое время:");
        for (Player player: players) {
            player.moodAction();
            if (Math.random() < 0.3) {
                if (player.isMale()) {
                    queen.speak("Отрубить ему голову");
                }
                else {
                    queen.speak("Отрубить ей голову");
                }
            }
        }

        queen.speak("Отрубить ей голову");
        alice.setMood(Mood.Wonder);
        alice.moodAction();
        alice.think("А что же тогда со мной будет?");
        alice.think("Ведь это ужас как они тут любят рубить людям головы; " +
                "прямо чудо, что кто-то ещё в живых остался!");

    }
}
