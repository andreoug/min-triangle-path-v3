package com.suprnation.openbook;

import lombok.*;
/**
 * Created by gandreou on 02/08/2021.
 */
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
        final StringBuffer sb = new StringBuffer("Node{");
        sb.append("value=").append(value);
        sb.append(", trValue=").append(trianglePathValue);
        sb.append(", trPath='").append(trianglePath).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
