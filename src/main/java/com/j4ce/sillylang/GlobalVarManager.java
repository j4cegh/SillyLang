package com.j4ce.sillylang;

public class GlobalVarManager {
    public static String getGlobalVarValue(String globalVar) {
        return Shared.globalVars.get(globalVar);
    }
}
