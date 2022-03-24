package com.j4ce.sillylang.base;

import org.w3c.dom.Node;

public class SillyException {

    public static void ThrowWithLocation(Node node, String desc) {
        String parentNodeName = node.getParentNode().getNodeName();
        String nodeName = node.getNodeName();

        SillyException.Throw(String.format("(at %s/%s) %s", parentNodeName, nodeName, desc));
        System.exit(1);
    }
    public static void Throw(String desc) {
        System.out.printf("An exception was thrown: %s%n", desc);
    }
}
