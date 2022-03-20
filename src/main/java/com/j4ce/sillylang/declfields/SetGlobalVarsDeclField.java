package com.j4ce.sillylang.declfields;

import com.j4ce.sillylang.Shared;
import com.j4ce.sillylang.util.Attributes;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SetGlobalVarsDeclField extends DeclField {
    public Node node;


    public SetGlobalVarsDeclField(Node node) {
        this.node = node;
    }

    @Override
    public void run() {

        NodeList globVarList = node.getChildNodes();

        // loop inside globvars declfield
        for (int itr = 0; itr < globVarList.getLength(); itr++) {
            if (globVarList.item(itr).getNodeName() == "var") {
                Node globVarDeclNode = globVarList.item(itr);
                String globVarDeclName = Attributes.GetAttributeValue(globVarDeclNode, "name");
                String globVarDeclVal = Attributes.GetAttributeValue(globVarDeclNode, "value");

                if (Shared.globalVars.containsKey(globVarDeclName))
                {
                    Shared.globalVars.remove(globVarDeclName);
                }
                Shared.globalVars.put(globVarDeclName, globVarDeclVal);
            }
        }
    }
}
