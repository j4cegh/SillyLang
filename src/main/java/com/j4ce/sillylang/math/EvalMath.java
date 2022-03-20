package com.j4ce.sillylang.math;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class EvalMath {
    public static int Eval(String possibleMath) throws ScriptException {
        Expression expression = new ExpressionBuilder(possibleMath).build();
        int result = (int) expression.evaluate();
        return result;
    }
}
