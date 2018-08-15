package main.ProgrammableDice;

import main.ProgrammableDice.Dice.History;

public interface Trigger {
    boolean isTriggered(History history);
}
