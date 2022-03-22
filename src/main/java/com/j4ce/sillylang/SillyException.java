package com.j4ce.sillylang;

import org.w3c.dom.Node;

public class SillyException {
    public static void ThrowWithLocation(Node node, String desc) {
        SillyException.Throw(String.format("(at %s/%s) %s", node.getParentNode().getNodeName(), node.getNodeName(), desc));
        System.exit(1);
    }
    public static void Throw(String desc) {
        System.out.println(String.format("An exception was thrown: %s", desc));
    }
}
