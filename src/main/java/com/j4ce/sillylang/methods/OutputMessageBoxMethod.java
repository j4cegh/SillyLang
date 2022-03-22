package com.j4ce.sillylang.methods;

import com.j4ce.sillylang.base.SillyException;
import com.j4ce.sillylang.util.Attributes;
import org.w3c.dom.Node;

public class OutputMessageBoxMethod extends Method {
    Node node;

    public OutputMessageBoxMethod(Node node) {
        this.node = node;
    }
    @Override
    public void run() {
        String title;
        String text;

        try {
            title = Attributes.GetAttributeValue(node, "title");
            text = Attributes.GetAttributeValue(node, "text");
        } catch (NullPointerException e) {
            SillyException.ThrowWithLocation(node, "You must set the title name.");
            System.exit(1);
        }


    }
}
