package main.ProgrammableDice;

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

    public ExactSequence(Trigger trigger, Integer... sequence) {
      //  this(trigger, Arrays.asList(sequence));
        super(trigger);
        this.sequence = Arrays.asList(sequence);
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
}
