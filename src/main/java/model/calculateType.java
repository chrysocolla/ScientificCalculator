package model;

/**
 *
 * @author Idris Opeyemi
 * @author Aristolochic, wangruory@bupt.edu.cn
 */
public class calculateType {
    private static String type;
    private static Boolean calculated = false;
    private static Boolean shiftMode = false;

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        calculateType.type = type;
    }

    public static Boolean getCalculated() {
        return calculated;
    }

    public static void setCalculated(Boolean calculated) {
        calculateType.calculated = calculated;
    }

    public static Boolean getShiftMode() {
        return shiftMode;
    }

    public static void setShiftMode(Boolean shiftMode) {
        calculateType.shiftMode = shiftMode;
    }
    
}
