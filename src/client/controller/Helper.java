package client.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.Period;

/**
 * Created by Marek on 17-May-17.
 */
public class Helper {

    protected static boolean validateEmptyField(TextField textField) {
        return !textField.getText().isEmpty();

    }

    /**
     * Validates if textfied contains only numbers.
     */

    protected static boolean validateNumberField(TextField textField) {
        return textField.getText().matches("[0-9]+");

    }

    /**
     * Validates if textfied contain double number.
     */

    protected static boolean validateDoubleNumberField(TextField textField) {
        return textField.getText().matches("[0-9]+.[0-9]+");

    }

    /**
     * Validates if textfield text has given length.
     */

    protected static boolean validateLength(TextField textField, int length) {
        if (length < 1) length = length * (-1);
        return textField.getText().length() == length;

    }

    /**
     * Validates if email is valid.
     */

    protected static boolean validateEmail(TextField textField) {
        return textField.getText().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

    }

    /**
     * Displays alert.
     *
     * @param title   title for alert
     * @param message alert's message
     */

    protected static void alertdisplay(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Displays success alert.
     *
     * @param title   tittle for mesage
     * @param message to show
     */

    protected static void successdisplay(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
