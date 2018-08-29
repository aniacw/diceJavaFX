package main.ProgrammableDice;

import main.ProgrammableDice.Dice.History;

public interface Trigger {
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


    boolean isTriggered(History history);
    void initialize(String input) throws ParseException;
}
