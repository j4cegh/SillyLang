package com.j4ce.sillylang;

public class GlobalVarManager {
    public static String getGlobalVarValue(String globalVar) {
        return Shared.globalVars.get(globalVar);
    }
    public static void setGlobalVar(String name, String value) {
        Shared.globalVars.remove(name);
        Shared.globalVars.put(name, value);
    }
}
