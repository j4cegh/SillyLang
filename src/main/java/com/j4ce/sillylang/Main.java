package com.j4ce.sillylang;

import com.j4ce.sillylang.base.SillyMain;

public class Main {

    public static void main(String[] args)
    {
        if (args.length > 0) {
            SillyMain _sillyMain = new SillyMain(args[0]);
            Thread langThread = new Thread(_sillyMain);
            langThread.start();
        } else {
            System.out.println("File not supplied.");
        }

    }

}
