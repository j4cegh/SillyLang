package com.j4ce.sillylang.base;

import com.j4ce.sillylang.methods.SetGlobalVarMethod;
import com.j4ce.sillylang.methods.WriteConsoleMethod;
import com.j4ce.sillylang.statements.ForLoopStatement;
import org.w3c.dom.Node;

import static com.j4ce.sillylang.base.Keywords.*;


public class SillyCode {
    /**
     * Executes every runnable supplied node.
     * @param node The (possibly executable) node.
     */
    public static void InterpretAndRun(Node node) {
        InterpretRunStatements(node);
        InterpretRunMethods(node);
    }

    private static void InterpretRunMethods(Node node) {

        switch (node.getNodeName()) {
            case M_OutputConsole: {
                WriteConsoleMethod writeConsoleMethod = new WriteConsoleMethod(node);
                writeConsoleMethod.run();
                break;
            }
            // sets singular global var
            case M_SetGlobalVar: {
                SetGlobalVarMethod setGlobalVarMethod = new SetGlobalVarMethod(node);
                setGlobalVarMethod.run();
            }
            case M_OutputMessageBox: {

                break;
            }
            default: {

            }
        }
    }

    private static void InterpretRunStatements(Node node) {
        switch (node.getNodeName()) {
            case S_ForLoop: {
                ForLoopStatement forLoopStatement = new ForLoopStatement(node);
                forLoopStatement.run();
                break;
            }
        }
    }
}
