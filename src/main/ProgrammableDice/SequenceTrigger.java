package main.ProgrammableDice;

import main.ProgrammableDice.Dice.History;

import java.util.Arrays;
import java.util.List;

public class SequenceTrigger implements Trigger {
    private List<Integer> sequence;

    public SequenceTrigger(List<Integer> sequence) {
        this.sequence = sequence;
    }

    public SequenceTrigger(Integer... sequence){
        this.sequence = Arrays.asList(sequence);
    }

    @Override
    public boolean isTriggered(History history) {
        return history.last(sequence.size()).equals(sequence);
    }
}
