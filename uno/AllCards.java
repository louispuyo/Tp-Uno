package uno;

import java.util.ArrayList;
import java.util.Collections;

public class AllCards {
    public ArrayList<Card> cards;

    AllCards() {
        this.cards = new ArrayList<>();

    }

    public Card getCard(int i) {
        return this.cards.get(i);

    }

    public boolean isEmpty() {
        return this.cards.isEmpty();
    }

    public int size() {
        return this.cards.size();
    }

    public void addCard(Card e) {
        this.cards.add(e);
    }

    public void removeCard(Card e) {
        this.cards.remove(e);
    }

    public void shuffleCards() {
        // Collections.shuffle(this.cards);
    }

    public Card get() {
        if (!isEmpty()) {
            return this.cards.remove(0);
        }
        return null;
    }

    public Card playI(int i) {
        if (!isEmpty()) {
            return this.cards.remove(i);
        }
        return null;

    }

    public boolean CanPlayCard(Card c) {

        boolean canPlay = false;
        Card lastCard = this.getCard(this.size() - 1);

        if (lastCard instanceof BasicCard) {
            BasicCard lastC = (BasicCard) lastCard;

            if (c instanceof BasicCard) {
                BasicCard BasicC = (BasicCard) c;

                if (BasicC.getColor().equals(lastC.getColor()) || BasicC.getValue() == lastC.getValue()) {
                    canPlay = true;
                } else {
                    SpeCard SCard = (SpeCard) c;

                    if (SCard.getColor().equals(lastCard.getColor()) || SCard.getEffect().equals("take4")
                            || SCard.getEffect().equals("chooseColor")) {
                        canPlay = true;
                    }
                }

            }

        }

        else // Si la defausse est une SpeCard
        {
            SpeCard lcard = (SpeCard) lastCard;

            if (c instanceof BasicCard) {
                BasicCard BasicC = (BasicCard) c;

                if (BasicC.getColor().equals(BasicC.getColor())) {
                    canPlay = true;
                }

            }

            else {
                SpeCard sCard = (SpeCard) c;

                // Si la defausse est un SuperJoker
                if (lcard.getEffect().equals("take4")) {

                    if ((sCard.getEffect().equals("chooseColor"))
                            || (sCard.getColor().equals(lcard.getColor()) && !sCard.getEffect().equals("take4"))) {
                        canPlay = true;
                    }

                } else {

                    if (sCard.getColor().equals(lcard.getColor()) || sCard.getEffect().equals(lcard.getEffect())
                            || sCard.getEffect().equals("chooseColor") || sCard.getEffect().equals("take4")) {
                        canPlay = true;
                    }
                }
            }
        }
        return canPlay;
    }
}

class TestAllCard extends AllCards {
    TestAllCard() {
        super();
    }

    public static void main(String[] args) {
        TestAllCard allcard = new TestAllCard();
        SpeCard s = new SpeCard("blue", "switchDirection");
        allcard.addCard(s);
        s.print();
        allcard.CanPlayCard(allcard.getCard(0));

    }
}