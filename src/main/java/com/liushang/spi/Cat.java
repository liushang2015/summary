package com.liushang.spi;

public class Cat implements IShout {
    @Override
    public void shout() {
        System.out.println("miao miao");
    }
}
