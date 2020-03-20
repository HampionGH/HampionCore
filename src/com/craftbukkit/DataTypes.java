package com.craftbukkit;

public enum DataTypes {

    INT("Integer"),
    DOUBLE("Double"),
    STRING("String"),
    FLOAT("Float"),
    BYTE("Byte"),
    SHORT("Short");

    public String name;

    private DataTypes (String name) {
        this.name = name;

    }



}
