package main.ProgrammableDice;

import main.ProgrammableDice.exception.ParseException;
import main.ProgrammableDice.exception.ProgramInitializationException;

import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class ReflectionProgramFactory extends ProgramFactory {
    private static final String packagePrefix = "main.ProgrammableDice.";


    @Override
    public Program createProgram(String programName, String programInput, String triggerName, String triggerInput) throws ProgramInitializationException {
        try {
            Class triggerClass = Class.forName(packagePrefix + triggerName);
            Constructor triggerCtor = triggerClass.getConstructor();
            Trigger trigger = (Trigger)triggerCtor.newInstance();
            trigger.initialize(triggerInput);
            Class programClass = Class.forName(packagePrefix + programName);
            Constructor programCtor = programClass.getConstructor(Trigger.class);
            Program program = (Program)programCtor.newInstance(trigger);
            program.initialize(programInput);
            return program;
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException | ParseException e) {
            e.printStackTrace();
            throw new ProgramInitializationException(programName, triggerName);
        }
    }

    @Override
    public List<String> getAvailableProgramNames() {
        return Arrays.asList(ExactSequence.class.getSimpleName());
    }

    @Override
    public List<String> getAvailableTriggerNames() {
        return Arrays.asList(SequenceTrigger.class.getSimpleName(), StringTrigger.class.getSimpleName());
    }
}
