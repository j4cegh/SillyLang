package com.j4ce.sillylang;

import com.j4ce.sillylang.methods.SetGlobalVarMethod;
import com.j4ce.sillylang.methods.WriteConsoleMethod;
import com.j4ce.sillylang.statements.ForLoopStatement;
import org.w3c.dom.Node;

public class SillyCode {

    public static void InterpretAndRun(Node node) {
        InterpretRunStatements(node);
        InterpretRunMethods(node);
    }

    private static void InterpretRunMethods(Node node) {
        switch (node.getNodeName()) {
            case "OutputConsole": {
                WriteConsoleMethod writeConsoleMethod = new WriteConsoleMethod(node);
                writeConsoleMethod.run();
                break;
            }
            // sets singular global var
            case "SetGlobalVar": {
                SetGlobalVarMethod setGlobalVarMethod = new SetGlobalVarMethod(node);
                setGlobalVarMethod.run();
            }
            case "OutputMessageBox": {
                break;
            }
            default: {

            }
        }
    }

    private static void InterpretRunStatements(Node node) {
        switch (node.getNodeName()) {
            case "For": {
                ForLoopStatement forLoopStatement = new ForLoopStatement(node);
                forLoopStatement.run();
                break;
            }
        }
    }
}
