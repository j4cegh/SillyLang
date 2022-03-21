package com.j4ce.sillylang.util;

import com.j4ce.sillylang.GlobalVarManager;
import com.j4ce.sillylang.exceptions.SillyException;
import com.j4ce.sillylang.math.EvalMath;
import org.w3c.dom.Node;

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
                case "text":
                case "string": {
                    String value = Vars.ReplaceEmbeddedVariables(Attributes.GetAttributeValue(node, "value"));
                    GlobalVarManager.setGlobalVar(name, value);
                    break;
                }

                case "int":
                case "number": {
                    String value = String.valueOf(EvalMath.ExpressionInt(Vars.ReplaceEmbeddedVariables(Attributes.GetAttributeValue(node, "value"))));
                    GlobalVarManager.setGlobalVar(name, value);
                    break;
                }
                default: {
                    throw new Exception();
                }
            }

        } catch (Exception e) {
            SillyException.ThrowSillyException("The value_type provided differs from the final result.");
        }
    }

    /**
     * Interprets and replaces the variables inserted inside a string using "[var_name]".
     * @param val The *potential* variable string.
     * @return The replaced value if there are any variables or the original value.
     */
    public static String ReplaceEmbeddedVariables(String val) {
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
