package com.j4ce.sillylang.methods;

import com.j4ce.sillylang.util.Vars;
import org.w3c.dom.Node;

import static com.j4ce.sillylang.util.Arguments.*;

public class WriteConsoleMethod extends Method {
    Node node;

    public WriteConsoleMethod(Node node) {
        this.node = node;
    }

    @Override
    public void run() {
        String attrTextValue = GetAttributeValue(node, "text");
        String finalText = Vars.InterpretAndReplaceVarString(attrTextValue);
        System.out.println(finalText);
    }
}