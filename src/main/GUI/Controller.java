package main.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.ProgrammableDice.*;
import main.ProgrammableDice.Dice.Dice;
import main.ProgrammableDice.exception.ProgramInitializationException;

import java.util.ArrayList;

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
    private ListView<Integer> history;

    @FXML
    private TextField triggerInput;

    @FXML
    private TextField programInput;

    public Controller() {
        dice = new Dice();
        programFactory = new ProgramFactory();
    }

    @FXML
    public void initialize() {
        for (String programName : programFactory.getAvailableProgramNames())
            comboBoxPrograms.getItems().addAll(programName);
        for (String triggerName : programFactory.getAvailableTriggerNames())
            comboBoxTriggers.getItems().addAll(triggerName);
        comboBoxPrograms.getSelectionModel().select(0);
        comboBoxTriggers.getSelectionModel().select(0);
    }

    public void onButtonAddClicked(ActionEvent actionEvent) {
        Trigger trigger = null;
        try {
            Program p = programFactory.createProgram(
                    comboBoxPrograms.getValue(), programInput.getText(), comboBoxTriggers.getValue(), triggerInput.getText());

            dice.addProgram(p);
            programList.getItems().add(p);
        } catch (ProgramInitializationException e) {
            labelInfo.setText(e.getMessage());
        }
    }

    public void onButtonRollClicked(ActionEvent actionEvent) {
        int rollResult = dice.roll();
        labelRollResult.setText(Integer.toString(rollResult));
        history.getItems().add(rollResult);
    }


    @FXML
    private ListView<Program> programList;
}
