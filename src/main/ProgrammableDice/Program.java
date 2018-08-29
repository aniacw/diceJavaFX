package main.ProgrammableDice;
import main.ProgrammableDice.Dice.Dice;

public abstract class Program {
    protected Trigger trigger;
    protected Dice dice;

    protected Program(Trigger trigger) {
        this.trigger = trigger;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public abstract boolean isFinished();

    public abstract int generateNumber();

    public Trigger getTrigger() {
        return trigger;
    }

    @Override
    public String toString() {
        return "Program{" +
                "trigger=" + trigger +
                '}';
    }
}
