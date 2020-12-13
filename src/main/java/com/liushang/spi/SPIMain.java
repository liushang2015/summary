package com.liushang.spi;

import java.util.ServiceLoader;

public class SPIMain {
    public static void main(String[] args) {
        ServiceLoader<IShout> shouts = ServiceLoader.load(IShout.class);
        shouts.iterator().next().shout();
        for (IShout s : shouts) {
            s.shout();
        }
    }
}
    