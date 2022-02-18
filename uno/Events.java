package uno;

import java.util.Hashtable;

/**
 * @contructor Events
 * @args String {command}
 *       fais le liens entre les commandes et les actions
 */
public class Events {

    protected String command;
    protected Action action;
    Hashtable<String, Action> actionsDict = new Hashtable<String, Action>();

    Events(String command) {

        this.action = actionsDict.get(command);

    }

    void printAction() {

        this.action.getClass().getName().toString();
    }

}

class EventByPlayer<Action> extends Events {

    protected String command;

    EventByPlayer(String command) {
        super(command);
        this.printAction();

    }

}
