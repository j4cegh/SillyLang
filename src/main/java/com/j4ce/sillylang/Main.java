package com.j4ce.sillylang;

import com.j4ce.sillylang.base.SillyMain;

public class Main {

    public static void main(String[] args)
    {
        SillyMain _sillyMain = new SillyMain();
        Thread langThread = new Thread(_sillyMain);
        langThread.start();
    }

}
