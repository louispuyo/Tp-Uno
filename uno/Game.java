package uno;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    AllCards pioche;
    AllCards stack;
    AllPlayers players;

    Game(int nbPlayers) {
        this.players = new AllPlayers();
        this.pioche = new AllCards();

        for (int i = 0; i < nbPlayers; i++) {
            for (int j = 0; j < 7; j++) {
                Player p = this.players.getpPlayer(i);
                p.getHands().addCard(this.pioche.get());

                this.players.add(p);
            }
        }

    }

    public static void main(String[] args) {

        Game g = new Game(4);

    }

}
