package com.suprnation.openbook;

import lombok.*;
/**
 * Created by gandreou on 02/08/2021.
 */
@Data
public class Node {
    private Integer value;
    private Node parent;
    private Integer trianglePathValue;

    public  Node(Node parent, int value) {
        this.parent = parent;
        this.value = value;
    }

    public Integer getTrianglePathValue(){
        if (trianglePathValue != null)
            return this.trianglePathValue;
        else {
            this.trianglePathValue = (parent != null) ? value + parent.getTrianglePathValue() : value;
//        System.out.println("#~#: getTrianglePathValue of value: " + value + ", parent: " + parent + ", output: " + trianglePathValue);
        }
        return this.trianglePathValue;
    }

    public String getTrianglePath(){
        String output = (parent != null) ? parent.getTrianglePath() + " + " + value.toString() : value.toString();
//        System.out.println("#~#: getTrianglePath of value: " + value + ", parent: " + parent + ", output: " +output);
        return output;
    }
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Node{");
        sb.append("value=").append(value);
        sb.append(",parent.value=").append((parent != null)? parent.value : "null");
        sb.append('}');
        return sb.toString();
    }
}
