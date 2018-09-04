package main.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.ProgrammableDice.*;
import main.ProgrammableDice.Dice.Dice;
import main.ProgrammableDice.Dice.History;
import main.ProgrammableDice.exception.ProgramInitializationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
    private Dice dice;
    private ProgramFactory programFactory;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonRoll;

    @FXML
    private ComboBox<String> comboBoxPrograms;

    @FXML
    private ComboBox<String> comboBoxTriggers;

    @FXML
    private Label labelInfo;

    @FXML
    private Label labelRollResult;

    @FXML
    private Label labelHistory;

    @FXML
    private Label labelSelectedProgram;

    @FXML
    private ListView<ArrayList> history;

    @FXML
    private TextField input;

    @FXML
    private TextField program;

    public Controller() {
        dice = new Dice();
        programFactory = new ProgramFactory();
    }

    @FXML
    public void initialize() {
        for (String programName : programFactory.getAvailableProgramNames())
            comboBoxPrograms.getItems().addAll(programName);

        comboBoxTriggers.getItems().add("Sequence Trigger");
        comboBoxTriggers.getItems().add("String Trigger");
    }

    public void onButtonAddClicked(ActionEvent actionEvent) {
        Trigger trigger = null;
        try {
            Program p = programFactory.createProgram(
                    comboBoxPrograms.getValue(), comboBoxTriggers.getValue());
            if (comboBoxTriggers.getValue() == "Sequence Trigger") {
                trigger = new SequenceTrigger(input.getText());
            }

            if (comboBoxTriggers.getValue() == "String Trigger") {
                 trigger = new StringTrigger(input.getText());
            }

            if (comboBoxPrograms.getValue() == "AddTwo") {
                Program addTwoProgram = new AddTwo(trigger, Integer.parseInt(program.getText()));
            }

            if (comboBoxPrograms.getValue() == "Exact Sequence") {
                Program exactSeqProgram = new ExactSequence(trigger, program.getText());
            }

            if (comboBoxPrograms.getValue() == "Random Number") {
                Program randomNumberProgram = new RandomNumber(trigger, Integer.parseInt(program.getText()));
            }

            if (comboBoxPrograms.getValue() == "Repeat Last Number") {
                Program repeatLastNumberProgram = new RepeatLastNumber(trigger, program.getText());
            }


            dice.addProgram(p);
            programList.getItems().add(p);
        } catch (ProgramInitializationException e) {
            labelInfo.setText(e.getMessage());
        }
    }

    public void onButtonRollClicked(ActionEvent actionEvent) {
        labelRollResult.setText(Integer.toString(dice.roll()));

//        for (int i = 0; i < dice.getHistory().getHistory().size(); i++) {
//            history.getItems().add(dice.getHistory().getHistory(i));
//        }

    }


    @FXML
    private ListView<Program> programList;
}
