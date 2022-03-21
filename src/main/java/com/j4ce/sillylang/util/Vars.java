package com.j4ce.sillylang.util;

import com.j4ce.sillylang.GlobalVarManager;
import com.j4ce.sillylang.math.EvalMath;
import org.w3c.dom.Node;

import javax.script.ScriptException;

public class Vars {
    /**
     * Sets a global variable with a node that contains a name, a value and a value_type.
     * @param node
     */
    public static void SetGlobalVarWithVarNode(Node node) {
        try {
            String name = Attributes.GetAttributeValue(node, "name");
            String value_type = Attributes.GetAttributeValue(node, "value_type");

            switch (value_type) {
                case "string": {
                    String value = Vars.InterpretAndReplaceVarString(Attributes.GetAttributeValue(node, "value"));
                    GlobalVarManager.setGlobalVar(name, value);
                    break;
                }
                case "number": {
                    String value = Vars.InterpretAndReplaceVarString(String.valueOf(Integer.parseInt(Attributes.GetAttributeValue(node, "value"))));
                    GlobalVarManager.setGlobalVar(name, value);
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("How did we even get here?");
        }
    }

    /**
     * Interprets and replaces the variables inserted inside a string using "[var_name]".
     * @param val The *potential* variable string.
     * @return The replaced value if there are any variables or the original value.
     */
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
