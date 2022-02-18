package uno;

import java.util.ArrayList;

import javax.lang.model.type.ArrayType;

public class Player {

    protected String name;
    protected int priority;
    protected int points;

    protected AllCards hands;

    public Player(String name) {
        this.name = name;
        this.priority = 0;
        this.hands = new AllCards();
        this.points = 0;

    }

    public int getScore() {
        return this.points;
    }

    public int getPriority() {
        return this.priority;
    }

    public AllCards getHands() {
        return this.hands;
    }

    /**
     * @method Canlie
     *         check si le joueur peu mentir / bluffer
     * @return boolean
     */

    public boolean CanLie() {

        if (this.hands.cards.contains(new SpeCard("black", "superJoker"))) {
            return true;

        }
        return false;
    }

}

class CreatePlayer extends Player

{
    protected String name;

    CreatePlayer(String name) {
        super(name);
    }

    public static void main(String[] args) {
        CreatePlayer p1 = new CreatePlayer("jo");
        SpeCard sp = new SpeCard("blue", "switchDirection");
        p1.hands.addCard(sp);
        if (p1.hands.cards.contains(sp)) {
            System.out.println("oui");
        } else

        {
            System.out.println("non");
        }

    }

}