package com.j4ce.sillylang.methods;

import com.j4ce.sillylang.util.Vars;
import org.w3c.dom.Node;

import static com.j4ce.sillylang.util.Attributes.*;

public class WriteConsoleMethod extends Method {
    Node node;

    public WriteConsoleMethod(Node node) {
        this.node = node;
    }

    @Override
    public void run() {
        // TODO: add types for console output
        String attrTextValue = GetAttributeValue(node, "text");
        String finalText = Vars.ReplaceEmbeddedVariables(attrTextValue);
        System.out.println(finalText);
    }
}
