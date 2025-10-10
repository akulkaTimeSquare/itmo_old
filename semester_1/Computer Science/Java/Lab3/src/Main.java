import creatures.*;
import creatures.animals.Flamingo;
import creatures.animals.Hedgehog;
import places.Mound;
import places.Pit;
import places.Plain;
import places.Square;

public class Main {
    public static void main(String[] args) {
        Mound annoyingMound = new Mound("Надоедливый бугор", 3);
        Pit annoyingPit = new Pit("Надоедливая ямка", 1);
        Plain crockettPlain = new Plain("Крокетное", 100, 75);
        Square royalSquare = new Square("Королевская", 9);

        Hedgehog aliceHedgehog = new Hedgehog(crockettPlain);
        Flamingo aliceFlamingo = new Flamingo(crockettPlain);
        Player alice = new Player("Алиса", false, Mood.Fine, VoiceTone.Optimistic,
                aliceFlamingo, aliceHedgehog, crockettPlain);
        Human queen = new Human("Королева",false, Mood.Fine, VoiceTone.Squeak, royalSquare);
        Soldier[] soldiers = new Soldier[] {new Soldier("Рони", true, Mood.Fine, VoiceTone.Rough, crockettPlain, royalSquare),
                new Soldier("Том", true, Mood.Sadness, VoiceTone.Serious, crockettPlain, royalSquare),
                new Soldier("Джек", true, Mood.Wonder, VoiceTone.Squeak, crockettPlain, royalSquare)};
        Player[] players = new Player[10];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player("Игрок#" + i, (Math.random() > 0.5), Mood.Fury, VoiceTone.Standard,
                    new Flamingo(crockettPlain), new Hedgehog(crockettPlain), crockettPlain);
        }

        System.out.println(alice.takeFlamingo());
        System.out.println();
        for (Soldier soldier : soldiers) {
            System.out.println(soldier.move());
        }
        System.out.println();
        System.out.println(alice.straightFlamingoNeck());
        System.out.println(alice.setHedgehogDirection(annoyingMound));
        System.out.println(alice.getOwnFlamingo().lookBehind());
        System.out.println(alice.moodAction());
        System.out.println(alice.straightFlamingoNeck());
        System.out.println(alice.getOwnHedgehog().move());
        alice.setMood(Mood.Sadness);
        System.out.println(alice.moodAction());
        System.out.println();

        System.out.println("В одно и то же время:");
        queen.setMood(Mood.Fury);
        for (Player player: players) {
            System.out.println(player.takeFlamingo());
            System.out.println(player.straightFlamingoNeck());
            System.out.println(player.hitHedgehog());
            if (Math.random() < 0.3) {
                if (player.isMale()) {
                    System.out.println(queen.speak("Отрубить ему голову"));
                }
                else {
                    System.out.println(queen.speak("Отрубить ей голову"));
                }
            }
        }

        System.out.println();
        System.out.println("Через некоторое время:");
        for (Player player: players) {
            System.out.println(player.moodAction());
            if (Math.random() > 0.3) {
                if (player.isMale()) {
                    System.out.println(queen.speak("Отрубить ему голову"));
                }
                else {
                    System.out.println(queen.speak("Отрубить ей голову"));
                }
            }
        }
    }
}
