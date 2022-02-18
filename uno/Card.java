package uno;

import java.lang.reflect.Field;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import uno.CardAttributs.Colors;

import java.lang.IllegalAccessException;

class CardAttributs {

    enum Colors {
        RED,
        BLUE,
        YELLOW,
        GREEN

    }

}

public abstract class Card {

    protected String color;

    Card(String color) {
        this.color = color;

    }

    public abstract String getColor();

    public abstract void print();

    public abstract boolean IsSwitchDirection();

    public abstract boolean IsChooseColor();

    public abstract boolean IsTake2();

    public abstract boolean IsTake4();

    public abstract boolean skipTurn();

    void PrintAllAttributs() {
        System.out.println(this.toString());
    }
}

class BasicCard extends Card {

    protected int value;
    protected String color;

    public BasicCard(int value, String color) {
        super(color);
        this.value = value;
        this.color = color;

    }

    public String getColor() {
        return this.color;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public void print() {
        System.out.println("color: " + this.color + ", value: " + this.value + "");

    }

    @Override
    public boolean IsSwitchDirection() {
        return false;
    }

    @Override
    public boolean IsChooseColor() {
        return false;
    }

    @Override
    public boolean IsTake2() {
        return false;
    }

    @Override
    public boolean IsTake4() {
        return false;
    }

    @Override
    public boolean skipTurn() {
        return false;
    }

}

class SpeCard extends Card {

    protected String effect;

    public SpeCard(String color, String effect) {
        super(color);
        this.effect = effect;

    }

    public String getColor() {
        return this.color;
    }

    public String getEffect() {
        return this.effect;

    }

    @Override
    public boolean IsSwitchDirection() {
        return this.getEffect().equals("switchDirection");
    }

    @Override
    public boolean IsChooseColor() {
        return this.getEffect().equals("chooseColor");
    }

    @Override
    public boolean IsTake2() {
        return this.getEffect().equals("take2");
    }

    @Override
    public boolean IsTake4() {
        return this.getEffect().equals("take4");
    }

    @Override
    public boolean skipTurn() {
        return this.getEffect().equals("skipTurn");
    }

    @Override
    public void print() {
        System.out.println("color: " + this.color + ", effect: " + this.effect + "");
    }

}

class Test extends SpeCard {

    protected CardAttributs color;
    protected int value;

    public static void main(String[] args) {
        Test card1 = new Test("yellow", "take2");
        card1.print();

    }

    Test(String color, String effect) {
        super("red", effect);

    }

}