package com.j4ce.sillylang.util;

import com.j4ce.sillylang.GlobalVarManager;

public class Vars {
    public static String InterpretAndReplaceVarString(String val) {
        try {
            String valueToExec = val.substring(val.indexOf("[") + 1);
            valueToExec = valueToExec.substring(0, valueToExec.indexOf("]"));
            String globalVarValue = GlobalVarManager.getGlobalVarValue(valueToExec);
            String finalVal = val;

            if (!valueToExec.isBlank() && globalVarValue != null) {
                finalVal = val.replace(String.format("[%s]", valueToExec), globalVarValue);
            }
            return finalVal;
        } catch (Exception e) {
            return val;
        }
    }
}
