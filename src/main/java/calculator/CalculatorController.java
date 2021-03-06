package calculator;

import calculator.model.Calculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.math.BigDecimal;

public class CalculatorController {

    @FXML
    private TextField display;
    @FXML
    private TextField formula;

    private Calculator calculator;
    private boolean startNumber = true;
    private String number1Str;
    private String operator = "";

    @FXML
    private void initialize() {
        calculator = new Calculator();
    }

    @FXML
    public void processDigit(ActionEvent event) {
        String digitPressed = ((Button) event.getSource()).getText();

        if (digitPressed.equals(".") && display.getText().contains("."))
            return;

        System.out.println(digitPressed);
        if (startNumber || display.getText().equals("0")) {
            display.setText(digitPressed);
        } else {
            display.setText(display.getText() + digitPressed);
        }
        startNumber = false;
    }

    @FXML
    public void processOperator(ActionEvent event) {

        if (display.getText().isEmpty())
            return;

        String operatorPressed = ((Button) event.getSource()).getText();
        System.out.println(operatorPressed);
        if (operatorPressed.equals("=")) {
           if (operator.isEmpty()) {
               return;
           }
           String number2Str = display.getText();
            var num1 = new BigDecimal(number1Str);
            var num2 = new BigDecimal(number2Str);
            var result = calculator.calculate(num1, num2, operator);
            display.setText(result.toString());
           formula.setText(String.format("%s %s %s %s", number1Str, operator, number2Str, "="));
           operator = "";

        } else {
            if (! operator.isEmpty()) {
                return;
            }
            number1Str = display.getText();
            operator = operatorPressed;
            startNumber = true;
            formula.setText(String.format("%s %s", number1Str, operator));
        }
    }

    @FXML
    public void processAC(ActionEvent event) {
        display.setText("");
        startNumber = true;
        operator = "";
        formula.setText("");
    }

    @FXML
    public void processSign(ActionEvent event) {
        String txt  = display.getText();
        if (txt.startsWith("-")) {
            txt = txt.substring(1);
        }
        else {
            txt = "-".concat(txt);
        }
        display.setText(txt);
    }

    public void processDelete(ActionEvent event) {
        String txt = display.getText();
        if (txt.length() == 0)
            return;

        txt = txt.substring(0, txt.length() - 1);
        display.setText(txt);
    }
}
