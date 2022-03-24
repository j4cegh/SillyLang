package com.j4ce.sillylang.methods;

import com.j4ce.sillylang.base.SillyException;
import com.j4ce.sillylang.util.Attributes;
import com.j4ce.sillylang.util.Vars;
import org.w3c.dom.Node;

import javax.swing.*;

public class OutputMessageBoxMethod extends Method {
    Node node;

    public OutputMessageBoxMethod(Node node) {
        this.node = node;
    }
    @Override
    public void run() {
        String title;
        String text;

        title = Vars.ReplaceEmbeddedVariables(Attributes.GetAttributeValue(node, "title", () ->
            SillyException.ThrowWithLocation(node, "You must set the messagebox title.")
        ));

        text = Vars.ReplaceEmbeddedVariables(Attributes.GetAttributeValue(node, "text", () ->
                SillyException.ThrowWithLocation(node, "You must set the messagebox text.")
        ));

        JOptionPane.showMessageDialog(null, text, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
