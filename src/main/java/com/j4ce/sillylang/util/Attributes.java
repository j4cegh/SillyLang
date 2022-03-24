package com.j4ce.sillylang.util;

import org.w3c.dom.Node;

public class Attributes {
    public interface Callback {
        void onError();
    }
    public static String GetAttributeValue(Node node, String attrName, Callback onErrorCallback)
    {
        String attributeValue = null;
        try {
            attributeValue = node.getAttributes().getNamedItem(attrName).getTextContent();
        } catch (NullPointerException e) {
            onErrorCallback.onError();
        }
        return attributeValue;
    }
}
