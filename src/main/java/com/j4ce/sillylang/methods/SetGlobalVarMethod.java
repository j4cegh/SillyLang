package com.j4ce.sillylang.methods;

import com.j4ce.sillylang.GlobalVarManager;
import com.j4ce.sillylang.math.EvalMath;
import com.j4ce.sillylang.util.Attributes;
import org.w3c.dom.Node;

import javax.script.ScriptException;

public class SetGlobalVarMethod extends Method {
    Node node;

    public SetGlobalVarMethod(Node node) {
        this.node = node;
    }
    @Override
    public void run() {
        try {
            String name = Attributes.GetAttributeValue(node, "name");
            String value = String.valueOf(EvalMath.Eval(Attributes.GetAttributeValue(node, "value")));

            GlobalVarManager.setGlobalVar(name, value);
        } catch (ScriptException e) {
            String name = Attributes.GetAttributeValue(node, "name");
            String value = Attributes.GetAttributeValue(node, "value");

            GlobalVarManager.setGlobalVar(name, value);
        }
    }
}
