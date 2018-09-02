package main.ProgrammableDice;

import main.ProgrammableDice.Dice.History;

import java.util.Arrays;
import java.util.List;

public class SequenceTrigger implements Trigger {

    private List<Integer> sequence;
    private String input;

    public SequenceTrigger(List<Integer> sequence) {
        this.sequence = sequence;
    }

    public SequenceTrigger() {
    }

    public SequenceTrigger(String input, Integer... sequence) {
        this.sequence = Arrays.asList(sequence);
        this.input = input;
    }

    @Override
    public boolean isTriggered(History history) {
        return history.last(sequence.size()).equals(sequence);
    }

    @Override
    public void initialize(String input) throws ParseException {
        sequence.clear();

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

    public String getInput() {
        return input;
    }
}
