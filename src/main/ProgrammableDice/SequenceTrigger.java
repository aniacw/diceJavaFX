package main.ProgrammableDice;

import main.ProgrammableDice.Dice.History;

import java.util.Arrays;
import java.util.List;

public class SequenceTrigger implements Trigger {

    private List<Integer> sequence;

    public SequenceTrigger(List<Integer> sequence) {
        this.sequence = sequence;
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

    public List<Integer> getSequence() {
        return sequence;
    }

    public void setSequence(List<Integer> sequence) {
        this.sequence = sequence;
    }
}
