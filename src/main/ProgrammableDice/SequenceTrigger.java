package main.ProgrammableDice;

import main.ProgrammableDice.Dice.History;
import main.ProgrammableDice.exception.ParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SequenceTrigger implements Trigger {

    private List<Integer> sequence;

    public SequenceTrigger(List<Integer> sequence) {
        this.sequence = sequence;
    }

    public SequenceTrigger() {
        this.sequence=new ArrayList<>();
    }

    public SequenceTrigger(Integer... sequence) {
        this.sequence = Arrays.asList(sequence);
    }

    @Override
    public boolean isTriggered(History history) {
        return history.last(sequence.size()).equals(sequence);
    }

    @Override
    public void initialize(String input) throws ParseException {
        sequence = new ArrayList<>();
        int n;
        String[] tokens;
        tokens = input.split("[,; ]+");
        try {
            for (String s : tokens) {
                n = Integer.parseInt(s);
                sequence.add(n);
            }
        } catch (NumberFormatException e) {
            throw new ParseException("", e);
        }
    }
}
