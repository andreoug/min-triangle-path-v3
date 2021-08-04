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

    public String getTrianglePath(){
        return trianglePath;
    }

    public Integer getTrianglePathValue(){
        return this.trianglePathValue;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Node{");
        sb.append("value=").append(value);
        sb.append(", trianglePathValue=").append(trianglePathValue);
        sb.append(", trianglePath='").append(trianglePath).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
