package viewcontroller.components;

import model.calculateType;
import viewcontroller.components.Screen;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
// FIXME 这里因为用了JavaScript出乎意料的简洁，但考虑到作者连溢出都不曾处理，我有充分的理由相信他并没有测试过
/**
 *
 * @author Idris Opeyemi
 * @author Aristolochic, wangruory@bupt.edu.cn
 */
public class Solve {

    private static ScriptEngine engine = new ScriptEngineManager().getEngineByExtension("js");

    public static void solveSci() {
        String calculate = Screen.getTypeField().getText();
        calculate = calculate.replace("sin-1", "Math.asin").
                replace("cos-1", "Math.acos").
                replace("tan-1", "Math.atan").
                replace("sin", "Math.sin").
                replace("cos", "Math.cos").
                replace("tan", "Math.tan").
                replace("√", "Math.sqrt").
                replace("∛", "Math.cbrt").
                replace("π", "Math.PI").
                replace("÷", "/").
                replace("%", "*1/100").
                replace("×", "*").
                replace("^", "**").
                replace("log", "Math.log");

        try {
            Object answer = engine.eval(calculate);
            Screen.getResultList().add(answer.toString());
            Screen.getResult().setText(answer.toString());
            calculateType.setCalculated(Boolean.TRUE);
        } catch (ScriptException e) {
            // Something went wrong
            Screen.getResultList().add("SYNTAX ERROR");
            Screen.getResult().setText("SYNTAX ERROR");
            calculateType.setCalculated(Boolean.TRUE);
            // e.printStackTrace();
        }
    }

    private static int factorial(int value) {
        for (int i = (value - 1); i > 0; i--) {
            value = value * i;
        }
        return value;
    }

    public static void combPerm() {
        String calculate = Screen.getTypeField().getText();
        if (calculate.contains("C")) {
            String val[] = calculate.split("(?=[C])|(?<=[C])");
            int nValue = Integer.parseInt(val[0]);
            int rValue = Integer.parseInt(val[2]);
            int nFactorial = factorial(nValue);
            int rFactorial = factorial(rValue);
            int nMinusRFactorial = factorial(nValue - rValue);
            int comb = nFactorial / (rFactorial * (nMinusRFactorial));
            Screen.getResultList().add(comb + "");
            calculateType.setCalculated(Boolean.TRUE);
            Screen.getResult().setText("" + comb);
        } else if (calculate.contains("P")) {
            String val[] = calculate.split("(?=[P])|(?<=[P])");
            int nValue = Integer.parseInt(val[0]);
            int rValue = Integer.parseInt(val[2]);
            int nFactorial = factorial(nValue);
            int nMinusRFactorial = factorial(nValue - rValue);
            int perm = nFactorial / (nMinusRFactorial);
            Screen.getResultList().add(perm + "");
            calculateType.setCalculated(Boolean.TRUE);
            Screen.getResult().setText("" + perm);
        }
    }
}
