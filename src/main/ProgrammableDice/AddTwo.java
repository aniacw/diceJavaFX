package main.ProgrammableDice;

public class AddTwo extends Program {
    private int repetitions;
    private int counter = 0;


    public AddTwo(Trigger trigger,  int repetitions) {
        super(trigger);
        this.repetitions = repetitions;
    }

    //ten jest dobrze?
    public AddTwo(Trigger trigger) {
        super(trigger);
        this.repetitions = repetitions;
        this.counter = counter;
    }

    @Override
    public boolean isFinished() {
        if (repetitions == counter) {
            counter = 0;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int generateNumber() {
        counter++;
        int n = dice.getHistory().last();
        n = (n + 2) % 6;
        return n;
    }

    @Override
    public String toString() {
        return "AddTwo{" +
                "repetitions=" + repetitions +
                "trigger=" + trigger+
                '}';
    }
}