import java.util.List;
import java.util.Stack;
/**
 * Класс для вычисления математических выражений
 */
public class ExpressionEvaluation {
    /**
     * Метод для вычисления математического выражения, основан на алгоритм сортировочной станции (shunting yard method).
     * @param expression список строк, представляющих математическое выражение
     * @return результат вычисления выражения
     */
    public double evaluateExpression(List<String> expression) {
        if (expression == null || expression.size() == 0) {
            return 0;
        }
        Stack<Double> numStack = new Stack<Double>();
        Stack<String> opStack = new Stack<String>();
        for (String token : expression) {
            if (isNumber(token)) {
                numStack.push(Double.valueOf(token));
            } else if (token.equals("(")) {
                opStack.push(token);
            } else if (token.equals(")")) {
                while (!opStack.peek().equals("(")) {
                    numStack.push(applyOperator(Double.valueOf(numStack.pop()), Double.valueOf(numStack.pop()), opStack.pop()));
                }
                opStack.pop();
            } else {
                while (!opStack.isEmpty() && priorityOfOperator(opStack.peek()) >= priorityOfOperator(token)) {
                    numStack.push(applyOperator(numStack.pop(), numStack.pop(), opStack.pop()));
                }
                opStack.push(token);
            }
        }
        while (!opStack.isEmpty()) {
            numStack.push(applyOperator(numStack.pop(), numStack.pop(), opStack.pop()));
        }
        return numStack.isEmpty() ? 0 : numStack.pop();
    }
    /**
     * Метод для проверки, является ли строка числом.
     * @param token строка для проверки
     * @return true, если строка является числом, иначе false
     */
    private boolean isNumber(String token) {
        return Character.isDigit(token.charAt(0));
    }
    /**
     * Метод для определения приоритета оператора.
     * @param operator оператор
     * @return приоритет оператора
     */
    private int priorityOfOperator(String operator){
        switch (operator){
            case ("("): return 0;
            case ("+"): return 1;
            case ("-"): return 1;
            case ("*"): return 2;
            case ("/"): return 2;
            case (")"): return 2;
            default: return 2;
        }

    }
    /**
     * Метод для выполнения операции над двумя числами с использованием оператора.
     * @param left левое число
     * @param right правое число
     * @param operatorToken оператор
     * @return результат операции
     */
    private double applyOperator(double left , double right, String operatorToken){
        char operator = operatorToken.charAt(0);
        switch (operator){
            case ('+'): return left + right;
            case ('-'): return right - left;
            case ('*'): return left * right;
            case ('/'): return (double) right / left;
            default: return right;
        }


    }
}