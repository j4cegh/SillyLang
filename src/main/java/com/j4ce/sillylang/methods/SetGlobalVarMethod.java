package com.j4ce.sillylang.methods;

import com.j4ce.sillylang.util.Vars;
import org.w3c.dom.Node;

public class SetGlobalVarMethod extends Method {

    Node node;

    public SetGlobalVarMethod(Node node) {
        this.node = node;
    }
    @Override
    public void run() {
        Vars.SetGlobalVarWithVarNode(node);
    }
}
