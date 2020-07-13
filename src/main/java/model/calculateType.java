package model;

/**
 *
 * @author Idris Opeyemi
 * @author Aristolochic, wangruory@bupt.edu.cn
 */
public class calculateType {
    private static Boolean workType = Boolean.FALSE;
    private static Boolean calculated =  Boolean.FALSE;
    private static Boolean shiftMode =  Boolean.FALSE;

    public static Boolean getWorkType() {
        return workType;
    }

    public static void setWorkType(Boolean workType) {
        calculateType.workType = workType;
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
