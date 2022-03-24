package com.j4ce.sillylang.util;

import com.j4ce.sillylang.base.SillyException;
import com.j4ce.sillylang.math.EvalMath;
import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException;
import org.w3c.dom.Node;

import javax.script.ScriptException;

import static com.j4ce.sillylang.base.Keywords.*;

public class Vars {
    /**
     * Sets a global variable from a node that contains a name, a value and a value_type.
     * @param node The variable node.
     */
    public static void SetGlobalVarWithVarNode(Node node) {
        String value_type;
        String name;
        String value;

        try {
            name = Attributes.GetAttributeValue(node, "name", () -> SillyException.ThrowWithLocation(node, "You must set the variable name."));

            value_type = Attributes.GetAttributeValue(node, "value_type", () -> SillyException.ThrowWithLocation(node, "You must set the variable value type."));
            value = Attributes.GetAttributeValue(node, "value", () -> SillyException.ThrowWithLocation(node, "You must set the variable value."));

            switch (value_type) {
                case T_StringLiteral, T_StringL, T_Float, T_Int, T_Number, T_Text, T_String, T_Double -> {
                    GlobalVarManager.setGlobalVar(name, value);
                }

                default -> {
                    SillyException.ThrowWithLocation(node, "Bad variable type supplied.");
                }
            }

        } catch (UnknownFunctionOrVariableException e) {
            SillyException.ThrowWithLocation(node, "Bad number/equation supplied.");
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
