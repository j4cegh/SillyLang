package com.j4ce.sillylang.declfields;

import com.j4ce.sillylang.Shared;
import com.j4ce.sillylang.util.Arguments;
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
                String globVarDeclName = Arguments.GetAttributeValue(globVarDeclNode, "name");
                String globVarDeclVal = Arguments.GetAttributeValue(globVarDeclNode, "value");

                if (Shared.globalVars.containsKey(globVarDeclName))
                {
                    Shared.globalVars.remove(globVarDeclName);
                }
                Shared.globalVars.put(globVarDeclName, globVarDeclVal);
            }
        }
    }
}
