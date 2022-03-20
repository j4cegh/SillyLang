package com.j4ce.sillylang;

import com.j4ce.sillylang.math.EvalMath;

public class Main {

    public static void main(String[] args)
    {
        Silly _silly = new Silly();
        Thread langThread = new Thread(_silly);
        langThread.start();
    }

}

