package uno;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    protected AllCards pioche;
    protected AllCards stack;
    protected AllPlayers players;

    public int nbPlayers;

    Game(int nbPlayers) {
        this.players = new AllPlayers();
        this.pioche = new AllCards();
        this.players.add(new Player("Moi"));
        this.nbPlayers = nbPlayers;

        for (int i = 0; i < nbPlayers; i++) {
            for (int j = 0; j < 7; j++) {
                Player p = this.players.getpPlayer(i);
                p.getHands().addCard(this.pioche.get());
                // la on fait pour teste mais faut melanger puis faire par la
                // pioche

                this.players.add(p);
            }
        }

    }

    /**
     * @methode addScore
     *          ajoute le score en fonction des cartes restantes a la fin
     *          de chaque
     *          tour
     */
    void addScore() {
        for (int j = 0; j < this.nbPlayers; j++) {
            Player p = players.getpPlayer(j);

            for (Card c : p.hands.cards) {
                p.points += c.value;
            }
        }
    }

    /**
     * @methode CheckLoose
     *          verifie que le score d aucun joueur atteint ou depasse
     *          500
     */
    public boolean checkLoose() {
        for (int j = 0; j < this.nbPlayers; j++) {
            if (this.players.getpPlayer(j).getScore() >= 500) {

                return true;
            }

        }
        return false;

    }

    /**
     * @param args
     * @return
     */
    public static void main(String[] args) {

        Game g = new Game(2);
        for (int j = 0; j < 7; j++) {
            g.players.getpPlayer(0).hands.cards.get(j).print();
        }

    }

}
