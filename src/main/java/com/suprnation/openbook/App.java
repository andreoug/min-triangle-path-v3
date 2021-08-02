package com.suprnation.openbook;

import java.util.Scanner;
/**
 * Created by gandreou on 02/08/2021.
 */
public final class App {

    private final static String SPLIT_REGEX = " ";

    public App() {}

    public static void main(String[] args) {

        TriangleGraph triangle = new TriangleGraph();
        int c = 0;
        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            System.out.println("read line: " + ++c);
            triangle.addElements(s.nextLine().split(SPLIT_REGEX));
        }

        triangle.findMinimalPath();
    }
}

