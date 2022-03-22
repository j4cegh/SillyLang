package com.j4ce.sillylang.base;

import com.j4ce.sillylang.Shared;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Map;

public class SillyInstance {
    Element mainMethod;

    public SillyInstance(Element mainMethod) {
        this.mainMethod = mainMethod;
    }

    public void Run() {
        if (mainMethod == null) {
            SillyException.Throw("The main method cannot be null.");
            System.exit(1);
        }

        NodeList mainMethodChildNodes = mainMethod.getChildNodes();
        for (Map.Entry<String, String> entry : Shared.globalVars.entrySet()) {
            System.out.println(String.format("GlobalVar '%s': %s", entry.getKey(), entry.getValue()));
        }
        System.out.println("\n");
        // main-method iter
        for (int itr = 0; itr < mainMethodChildNodes.getLength(); itr++)
        {
            Node node = mainMethodChildNodes.item(itr);

            SillyCode.InterpretAndRun(node);
        }

    }
}
