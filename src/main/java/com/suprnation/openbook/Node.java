package com.suprnation.openbook;

import lombok.Data;

@Data
public class Node {
    private Integer value;
    private Integer trianglePathValue;
    private String trianglePath;

    public  Node(int value, String trianglePath, Integer trianglePathValue) {
        this.value = value;
        this.trianglePath = trianglePath;
        this.trianglePathValue = trianglePathValue;
    }

    @Override
    public String toString() {
        return "Node{" + "value=" + value +
                ", trValue=" + trianglePathValue +
                ", trPath='" + trianglePath + '\'' +
                '}';
    }
}
