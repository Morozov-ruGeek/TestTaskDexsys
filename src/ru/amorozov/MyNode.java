package ru.amorozov;

import java.util.Objects;

public class MyNode {
    int x;
    int y;

    public MyNode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
