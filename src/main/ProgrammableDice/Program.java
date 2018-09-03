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

    class ParseException extends Exception {
        public ParseException() {
        }

        public ParseException(String message) {
            super(message);
        }

        public ParseException(String message, Throwable cause) {
            super(message, cause);
        }

        public ParseException(Throwable cause) {
            super(cause);
        }
    }

    abstract public void initialize(String input) throws ParseException;
}
