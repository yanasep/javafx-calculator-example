package calculator;

import calculator.model.Calculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML
    private TextField display;

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
        String operatorPressed = ((Button) event.getSource()).getText();
        System.out.println(operatorPressed);
        if (operatorPressed.equals("=")) {
           if (operator.isEmpty()) {
               return;
           }
           String number2Str = display.getText();
           if (!number1Str.contains(".") && !number2Str.contains(".") && !operator.equals("/")) {
               // Integer calculation
               int number1 = Integer.parseInt(number1Str);
               int number2 = Integer.parseInt(number2Str);
               int result = calculator.calculateInt(number1, number2, operator);
               display.setText(String.format("%d", result));
           }
           else {
               // double calculation
               double number1 = Double.parseDouble(number1Str);
               double number2 = Double.parseDouble(number2Str);
               double result = calculator.calculate(number1, number2, operator);
               display.setText(String.format("%.5f", result));
           }
           operator = "";

        } else {
            if (! operator.isEmpty()) {
                return;
            }
            number1Str = display.getText();
            operator = operatorPressed;
            startNumber = true;
        }
    }

    @FXML
    public void processAC(ActionEvent event) {
        display.setText("");
        startNumber = true;
        operator = "";
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
}
