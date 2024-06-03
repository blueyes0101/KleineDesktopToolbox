package test1;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class calcuController {

    @FXML
    private TextField display;

    private double num1 = 0;
    private String operator = "";
    private boolean start = true;

    @FXML
    private void onNumberClick(javafx.event.ActionEvent event) {
        if (start) {
            display.clear();
            start = false;
        }
        String value = ((Button) event.getSource()).getText();
        display.appendText(value);
    }

    @FXML
    private void onOperatorClick(javafx.event.ActionEvent event) {
        String value = ((Button) event.getSource()).getText();
        if (!value.equals("=")) {
            if (!operator.isEmpty())
                return;

            operator = value;
            num1 = Double.parseDouble(display.getText());
            display.clear();
        } else {
            if (operator.isEmpty())
                return;

            double num2 = Double.parseDouble(display.getText());
            double result = calculate(num1, num2, operator);
            display.setText(String.valueOf(result));
            operator = "";
            start = true;
        }
    }

    @FXML
    private void onEqualsClick(javafx.event.ActionEvent event) {
        onOperatorClick(event);
    }

    private double calculate(double num1, double num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0)
                    return 0;
                return num1 / num2;
            default:
                return 0;
        }
    }

    @FXML
    private Button btnBack;

    @FXML
    private void onBackBtn() throws IOException {
        App.showMenu();
    }
}
