package main.ProgrammableDice;

import java.util.Random;

public class RandomNumber extends Program {
    private Random random;
    private int bound;

    public RandomNumber(Trigger trigger, int bound) {
        super(trigger);
        random = new Random();
        this.bound = bound;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public int generateNumber() {
        return random.nextInt(bound) + 1;
    }
}