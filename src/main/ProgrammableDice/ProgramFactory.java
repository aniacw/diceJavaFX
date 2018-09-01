package main.ProgrammableDice;

import main.ProgrammableDice.exception.ProgramInitializationException;

import java.util.Arrays;
import java.util.List;

public class ProgramFactory {
    private static final String EXACT_SEQUENCE_NAME = "Exact Sequence";
    private static final String ADD_TWO_NAME = "Add Two";
    private static final String RANDOM_NUMBER_NAME = "Random Number";
    private static final String REPEAT_LAST_NUMBER_NAME = "Repeat Last Number";

    public Program createProgram(String programName, String triggerName) throws ProgramInitializationException {
        if (programName == null || triggerName == null) {
            throw new ProgramInitializationException(programName, triggerName);
        }
        Program program;
        Trigger trigger;

        if (triggerName.equals("Sequence Trigger")) {
            trigger = new SequenceTrigger(List<Integer>);
        } else if (triggerName.equals("String Trigger")) {
            trigger = new StringTrigger();
        } else {
            throw new ProgramInitializationException(programName, triggerName);
        }

        if (programName.equals(EXACT_SEQUENCE_NAME)) {
            program = new ExactSequence(trigger);
        } else if (programName.equals(ADD_TWO_NAME)) {
            program = new AddTwo(trigger, 2);
        } else if (programName.equals(RANDOM_NUMBER_NAME)) {
            program = new RandomNumber(trigger, 6);
        } else if (programName.equals(REPEAT_LAST_NUMBER_NAME)) {
            program = new RepeatLastNumber(trigger, 3);

        } else {
            throw new ProgramInitializationException(programName, triggerName);
        }

        return program;
    }


    public List<String> getAvailableProgramNames() {
        return Arrays.asList(EXACT_SEQUENCE_NAME, ADD_TWO_NAME, RANDOM_NUMBER_NAME, REPEAT_LAST_NUMBER_NAME);
    }

}
