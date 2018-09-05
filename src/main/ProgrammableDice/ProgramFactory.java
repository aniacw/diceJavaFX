package main.ProgrammableDice;

import main.ProgrammableDice.exception.ParseException;
import main.ProgrammableDice.exception.ProgramInitializationException;

import java.util.Arrays;
import java.util.List;

public class ProgramFactory {
    private static final String EXACT_SEQUENCE_NAME = "Exact Sequence";
    private static final String ADD_TWO_NAME = "Add Two";
    private static final String RANDOM_NUMBER_NAME = "Random Number";
    private static final String REPEAT_LAST_NUMBER_NAME = "Repeat Last Number";

    private static final String SEQUENCE_TRIGGER_NAME = "Sequence Trigger";
    private static final String STRING_TRIGGER_NAME = "String Trigger";

    public Program createProgram(String programName, String programInput, String triggerName, String triggerInput) throws ProgramInitializationException {
        if (programName == null || triggerName == null) {
            throw new ProgramInitializationException(programName, triggerName);
        }
        Program program;
        Trigger trigger;
        try {
            if (triggerName.equals(SEQUENCE_TRIGGER_NAME)) {
                trigger = new SequenceTrigger();
            } else if (triggerName.equals(STRING_TRIGGER_NAME)) {
                trigger = new StringTrigger();
            } else {
                throw new ProgramInitializationException(programName, triggerName);
            }
            trigger.initialize(triggerInput);
        } catch (ParseException e) {
            throw new ProgramInitializationException(programName, triggerName);
        }
        try {
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
            program.initialize(programInput);
        } catch (ParseException e) {
            throw new ProgramInitializationException(programName, triggerName);
        }
        return program;
    }


    public List<String> getAvailableProgramNames() {
        return Arrays.asList(EXACT_SEQUENCE_NAME, ADD_TWO_NAME, RANDOM_NUMBER_NAME, REPEAT_LAST_NUMBER_NAME);
    }

    public List<String> getAvailableTriggerNames(){
        return Arrays.asList(SEQUENCE_TRIGGER_NAME, STRING_TRIGGER_NAME);
    }

}
