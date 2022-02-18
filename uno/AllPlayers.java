package uno;

import java.util.ArrayList;
import uno.Player;

class AllPlayers {

    private ArrayList<Player> playersList;

    AllPlayers() {

        this.playersList = new ArrayList<>();

    }

    public int size() {
        return this.playersList.size();
    }

    public Player getpPlayer(int i) {
        return this.playersList.get(i);

    }

    public void add(Player p) {
        int i = 0;
        while ((i < playersList.size()) && (playersList.get(i).getPriority() <= p.getPriority())) {
            i++;
        }
        playersList.add(i, p);

    }

    public boolean isEmpty() {
        return this.playersList.isEmpty();
    }

    public int PosPlayer() {
        boolean find = false;
        int position = 0;

        for (int i = 0; i < this.playersList.size(); i++) {
            if (this.playersList.get(i) instanceof Player) {
                find = true;
                position = i;
            }
        }
        return position;
    }
}

class TestAllPlayer extends AllPlayers {
    TestAllPlayer() {
        super();
    }

    public static void main(String[] args) {
        TestAllPlayer p = new TestAllPlayer();

    }
}