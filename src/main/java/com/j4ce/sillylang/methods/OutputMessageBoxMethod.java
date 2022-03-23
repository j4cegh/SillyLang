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
        String title = null;
        String text = null;

        try {
            title = Vars.ReplaceEmbeddedVariables(Attributes.GetAttributeValue(node, "title"));
        } catch (NullPointerException e) {
            SillyException.ThrowWithLocation(node, "You must set the messagebox title.");
            System.exit(1);
        }
        try {
            text = Vars.ReplaceEmbeddedVariables(Attributes.GetAttributeValue(node, "text"));
        } catch (NullPointerException e) {
            SillyException.ThrowWithLocation(node, "You must set the messagebox text.");
            System.exit(1);
        }
        JOptionPane.showMessageDialog(null, text, title, JOptionPane.INFORMATION_MESSAGE);


    }
}
