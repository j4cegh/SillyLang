package com.j4ce.sillylang.exceptions;

public class SillyException {
    public static void ThrowSillyException(String desc) {
        System.out.println(String.format("An exception was thrown: %s", desc));
    }
}
