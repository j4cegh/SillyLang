package com.j4ce.sillylang.util;

import com.j4ce.sillylang.GlobalVarManager;
import com.j4ce.sillylang.exceptions.SillyException;
import com.j4ce.sillylang.exceptions.variables.VarNameException;
import com.j4ce.sillylang.exceptions.variables.VarValueException;
import com.j4ce.sillylang.exceptions.variables.VarValueTypeException;
import com.j4ce.sillylang.math.EvalMath;
import org.w3c.dom.Node;

import javax.script.ScriptException;

public class Vars {
    /**
     * Sets a global variable with a node that contains a name, a value and a value_type.
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
                throw new VarNameException();
            }
            try {
                value_type = Attributes.GetAttributeValue(node, "value_type");
            } catch(NullPointerException e) {
                throw new VarValueTypeException();
            }
            switch (value_type) {
                case "text":
                case "string": {
                    try {
                        value = Vars.ReplaceEmbeddedVariables(Attributes.GetAttributeValue(node, "value"));
                    } catch (NullPointerException e) {
                        throw new VarValueException();
                    }
                    GlobalVarManager.setGlobalVar(name, value);
                    break;
                }

                case "int":
                case "number": {
                    value = String.valueOf(EvalMath.ExpressionInt(Vars.ReplaceEmbeddedVariables(Attributes.GetAttributeValue(node, "value"))));
                    GlobalVarManager.setGlobalVar(name, value);
                    break;
                }
                default: {
                    SillyException.ThrowSillyException(String.format("(at %s/%s) Bad variable type supplied.", node.getParentNode().getNodeName(), node.getNodeName()));
                    System.exit(1);
                }
            }

        } catch (ScriptException e) {
            SillyException.ThrowSillyException(String.format("(at %s/%s) Bad number/equation supplied.", node.getParentNode().getNodeName(), node.getNodeName()));
            System.exit(1);
        }
        catch (VarNameException e) {
            SillyException.ThrowSillyException(String.format("(at %s/%s) You must set the variable name.", node.getParentNode().getNodeName(), node.getNodeName()));
            System.exit(1);
        }
        catch (VarValueTypeException e) {
            SillyException.ThrowSillyException(String.format("(at %s/%s) You must set the variable value type.", node.getParentNode().getNodeName(), node.getNodeName()));
            System.exit(1);
        }
        catch (VarValueException e) {
            SillyException.ThrowSillyException(String.format("(at %s/%s) You must set the variable value.", node.getParentNode().getNodeName(), node.getNodeName()));
            System.exit(1);
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
