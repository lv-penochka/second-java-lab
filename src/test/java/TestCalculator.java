import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCalculator {

    @Test
    public void test_expressions() {
        ExpressionParsing exp1 = new ExpressionParsing("1 + 2 + 3");
        assertEquals(exp1.calcExpression(), 6);
        ExpressionParsing exp2 = new ExpressionParsing("(5 + 3) * 2");
        assertEquals(exp2.calcExpression(), 16);
        ExpressionParsing exp3 = new ExpressionParsing("5.2 + 2.4");
        assertEquals(exp3.calcExpression(), 7.6, 0.00001);
        ExpressionParsing exp4 = new ExpressionParsing("5.6 / 2");
        assertEquals(exp4.calcExpression(), 2.8, 0.00001);

        Assertions.assertThrows(RuntimeException.class, () -> {
            ExpressionParsing exp5 = new ExpressionParsing("((5 + 3) * 2");
            exp5.calcExpression();
        });
    }
}