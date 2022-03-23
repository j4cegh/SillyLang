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
        String value_type = "";
        String name = "";
        String value = "";

        try {
            try {
                name = Attributes.GetAttributeValue(node, "name");
            } catch(NullPointerException e) {
                SillyException.ThrowWithLocation(node, "You must set the variable name.");
                System.exit(1);
            }
            try {
                value_type = Attributes.GetAttributeValue(node, "value_type");
            } catch(NullPointerException e) {
                SillyException.ThrowWithLocation(node, "You must set the variable value type.");
                System.exit(1);
            }
            switch (value_type) {
                case T_StringLiteral, T_StringL -> {
                    try {
                        value = Attributes.GetAttributeValue(node, "value");
                    } catch (NullPointerException e) {
                        SillyException.ThrowWithLocation(node, "You must set the variable value.");
                    }
                    GlobalVarManager.setGlobalVar(name, value);
                }
                case T_Text, T_String -> {
                    try {
                        value = Vars.ReplaceEmbeddedVariables(Attributes.GetAttributeValue(node, "value"));
                    } catch (NullPointerException e) {
                        SillyException.ThrowWithLocation(node, "You must set the variable value.");
                    }
                    GlobalVarManager.setGlobalVar(name, value);
                }
                case T_Int, T_Number -> {
                    try {
                        value = String.valueOf(EvalMath.ExpressionInt(Vars.ReplaceEmbeddedVariables(Attributes.GetAttributeValue(node, "value"))));
                    } catch (NullPointerException e) {
                        SillyException.ThrowWithLocation(node, "You must set the variable value.");
                    }
                    GlobalVarManager.setGlobalVar(name, value);
                }
                case T_Float -> {
                    try {
                        value = String.valueOf(EvalMath.ExpressionFloat(Vars.ReplaceEmbeddedVariables(Attributes.GetAttributeValue(node, "value"))));
                    } catch (NullPointerException e) {
                        SillyException.ThrowWithLocation(node, "You must set the variable value.");
                    }
                    GlobalVarManager.setGlobalVar(name, value);
                }
                default -> {
                    SillyException.ThrowWithLocation(node, "Bad variable type supplied.");
                }
            }

        } catch (ScriptException | UnknownFunctionOrVariableException e) {
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
