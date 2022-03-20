package com.j4ce.sillylang.util;

import org.w3c.dom.Node;

public class Attributes {
    public static String GetAttributeValue(Node node, String attrName)
    {
        return node.getAttributes().getNamedItem(attrName).getTextContent();
    }
}
