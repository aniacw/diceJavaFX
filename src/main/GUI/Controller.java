package main.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import main.ProgrammableDice.*;
import main.ProgrammableDice.Dice.Dice;
import main.ProgrammableDice.exception.ProgramInitializationException;

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

    public Controller(){
        dice = new Dice();
        programFactory = new ProgramFactory();
    }

    @FXML
    public void initialize(){
        for (String programName : programFactory.getAvailableProgramNames())
            comboBoxPrograms.getItems().addAll(programName);

        comboBoxTriggers.getItems().add("Sequence Trigger");
        comboBoxTriggers.getItems().add("String Trigger");
    }

    public void onButtonAddClicked(ActionEvent actionEvent) {
        try {
            dice.addProgram(
                    programFactory.createProgram(
                            comboBoxPrograms.getValue(), comboBoxTriggers.getValue()));
        } catch (ProgramInitializationException e) {
            labelInfo.setText(e.getMessage());
        }
    }

    public void onButtonRollClicked(ActionEvent actionEvent) {
        labelRollResult.setText(Integer.toString(dice.roll()));
    }
}
