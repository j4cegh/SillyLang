package com.j4ce.sillylang.statements;

import org.w3c.dom.Node;

public class WhileLoopStatement extends Statement {
    Node node;

    public WhileLoopStatement(Node node) {
        this.node = node;
    }

    @Override
    public void run() {

    }
}
