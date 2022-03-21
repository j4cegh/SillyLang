package com.j4ce.sillylang.statements;

import com.j4ce.sillylang.SillyCode;
import com.j4ce.sillylang.math.EvalMath;
import com.j4ce.sillylang.util.Vars;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.script.ScriptException;

import static com.j4ce.sillylang.util.Attributes.*;

public class ForLoopStatement extends Statement {
    Node node;

    public ForLoopStatement(Node node) {
        this.node = node;
    }

    @Override
    public void run() {
        String rangeString = Vars.ReplaceEmbeddedVariables(GetAttributeValue(node, "range"));
        try {
            int range = EvalMath.ExpressionInt(rangeString);
            NodeList forLoopChildNodes = node.getChildNodes();

            // user loop, not get child loop
            for(int itr = 0; itr < range; itr++) {
                // get forloop child nodes and execute them
                for (int itr2 = 0; itr2 < forLoopChildNodes.getLength(); itr2++)
                {
                    Node loopedNode = forLoopChildNodes.item(itr2);
                    SillyCode.InterpretAndRun(loopedNode);
                }
            }
        } catch (ScriptException e) {

        } catch(NumberFormatException e) {

        }



    }
}
