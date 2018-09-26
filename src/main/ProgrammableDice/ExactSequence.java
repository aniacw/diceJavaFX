package main.ProgrammableDice;

import main.ProgrammableDice.exception.ParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class ExactSequence extends Program {
    private List<Integer> sequence;
    private ListIterator<Integer> iterator;

    public ExactSequence(Trigger trigger, List<Integer> sequence) {
        super(trigger);
        this.sequence = sequence;
        this.iterator = this.sequence.listIterator();
    }

    public ExactSequence(Trigger trigger) {
      //  this(trigger, Arrays.asList(sequence));
        super(trigger);
        this.sequence = new ArrayList<>();
        this.iterator = this.sequence.listIterator();
    }

    @Override
    public boolean isFinished() {
        if (!iterator.hasNext()) {
            iterator = sequence.listIterator();
            return true;
        } else
            return false;
    }

    @Override
    public int generateNumber() {
        return iterator.next();
    }

    @Override
    public String toString() {
        return "ExactSequence{" +
                "sequence=" + sequence +
                "trigger=" + trigger +
                '}';
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
        iterator=sequence.listIterator();
    }
}
