package calculator.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {

    public BigDecimal calculate(BigDecimal number1, BigDecimal number2, String operator) {
        switch (operator) {
            case "+":
                return number1.add(number2).stripTrailingZeros();
            case "-":
                return number1.subtract(number2).stripTrailingZeros();
            case "*":
                return number1.multiply(number2).stripTrailingZeros();
            case "/":
                return number1.divide(number2, 10, RoundingMode.HALF_UP).stripTrailingZeros();
        }
        throw new UnsupportedOperationException();
    }

}
