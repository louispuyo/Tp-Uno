package uno;

public abstract class Action {

    protected String command;

    Action() {
        this.command = command;

    }

    abstract public void pickCard();

    abstract public void PassTurn();

    // abstract public void CallengeSomeone(Player p);

    // abstract public void playCard(Card c);

}
