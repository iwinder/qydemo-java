package base;

import Utills.PrintUtill;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DoubleDemo {
    public static void main(String[] args) {
        Double a = 10.31;
        Double b = 9.01;
        PrintUtill.println(a-b);
        PrintUtill.printlnRule();
        BigDecimal c = new BigDecimal(a);
        BigDecimal d = new BigDecimal(b);
        PrintUtill.println(c.subtract(d).setScale(2, RoundingMode.HALF_UP).doubleValue());
    }
}
