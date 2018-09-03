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
        try {
            Program p = programFactory.createProgram(
                    comboBoxPrograms.getValue(), comboBoxTriggers.getValue());
            if (comboBoxTriggers.getValue() == "Sequence Trigger") {
                Trigger seqTrigger = new SequenceTrigger(input.getText());
            }

            if (comboBoxTriggers.getValue() == "String Trigger") {
                Trigger strTrigger = new StringTrigger(input.getText());
            }

            dice.addProgram(p);
            programList.getItems().add(p);
        } catch (ProgramInitializationException e) {
            labelInfo.setText(e.getMessage());
        }
    }

    public void onButtonRollClicked(ActionEvent actionEvent) {
        labelRollResult.setText(Integer.toString(dice.roll()));

        for (int i = 0; i < dice.getHistory().getHistory().size(); i++) {
            history.getItems().add(dice.getHistory().getHistory(i));
        }

    }


    @FXML
    private ListView<Program> programList;
}
