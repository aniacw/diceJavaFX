package main.ProgrammableDice.Dice;

import main.ProgrammableDice.Program;
import main.ProgrammableDice.RandomNumber;
import main.ProgrammableDice.Trigger;
import main.ProgrammableDice.exception.ParseException;

import java.util.ArrayList;
import java.util.List;

public class Dice {

    private static final Program defaultProgram = new RandomNumber(new Trigger() {
        @Override
        public boolean isTriggered(History history) {
            return true;
        }

        @Override
        public void initialize(String input) throws ParseException {

        }
    }, 6);

    private List<Program> programs;
    private Program currentProgram;
    private History history;

    public Dice() {
        programs = new ArrayList<>();
        currentProgram = defaultProgram;
        programs.add(currentProgram);
        history = new History();
    }

    public Dice(Program defaultProgram) {
        programs = new ArrayList<>();
        currentProgram = defaultProgram;
        programs.add(currentProgram);
        history = new History();
    }

    public void addProgram(Program program) {
        programs.add(0, program);
        program.setDice(this);
    }


    public int roll() {
        int number = currentProgram.generateNumber();
        history.add(number);
        if (currentProgram.isFinished()) {
            for (Program p : programs) {
                if (p.getTrigger().isTriggered(history)) {
                    currentProgram = p;
                    break;
                }
            }
        }
        return number;
    }

    public History getHistory() {
        return history;
    }

    public void reset(){
        history.clear();
        currentProgram=defaultProgram;
    }

}
//ma dodawac do historii
//ma odpowiadać za generowanie tych wartości przy pomocy programów.
//Powinna natomiast mieć instancję historii i ostatecznie przy rzucie pobierać liczbę z aktualnego programu,
// dodawać do historii, robić wszystkie inne sprawdzenia np czy nie odpaliliśmy innego programu i ostatecznie zwracać ten numer