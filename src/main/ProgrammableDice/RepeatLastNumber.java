package main.ProgrammableDice;

public class RepeatLastNumber extends Program {

    private int repetitions;
    private int counter;

    protected RepeatLastNumber(Trigger trigger, int repetitions) {
        super(trigger);
        this.repetitions = repetitions;
        this.counter = 0;
    }

    @Override
    public boolean isFinished() {
        if (counter == repetitions) {
            counter = 0;
            return true;
        } else
            return false;
    }

    @Override
    public int generateNumber() {
        ++counter;
        return dice.getHistory().last();
    }

    @Override
    public String toString() {
        return "RepeatLastNumber{" +
                "repetitions=" + repetitions +
                "trigger=" + trigger +
                '}';
    }
}