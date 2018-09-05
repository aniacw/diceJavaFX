package main.ProgrammableDice;

import main.ProgrammableDice.Dice.History;
import main.ProgrammableDice.exception.ParseException;

public interface Trigger {
    boolean isTriggered(History history);
    void initialize(String input) throws ParseException;
}
