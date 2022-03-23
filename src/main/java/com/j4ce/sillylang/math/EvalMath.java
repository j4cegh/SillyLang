package com.j4ce.sillylang.math;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import javax.script.ScriptException;

public class EvalMath {
    public static int ExpressionInt(String possibleMath) throws ScriptException {
        Expression expression = new ExpressionBuilder(possibleMath).build();
        return (int) expression.evaluate();
    }
    public static float ExpressionFloat(String possibleMath) throws ScriptException {
        Expression expression = new ExpressionBuilder(possibleMath).build();
        return (float) expression.evaluate();
    }
    public static double ExpressionDouble(String possibleMath) throws ScriptException {
        Expression expression = new ExpressionBuilder(possibleMath).build();
        return expression.evaluate();
    }
}
