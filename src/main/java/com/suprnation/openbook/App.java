package com.suprnation.openbook;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by gandreou on 02/08/2021.
 */
public final class App {

    private final static String SPLIT_REGEX = " ";

    private static List lines = new ArrayList();

    public App() {}

    public static void main(String[] args) {

        TriangleGraph triangle = new TriangleGraph();
        int c = 0;
        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            System.out.println("read line: " + ++c);
            lines.add(Arrays.stream(s.nextLine().split(SPLIT_REGEX))
                    .map(Integer::parseInt).collect(Collectors.toList()));
        }
        Collections.reverse(lines);
        lines.stream().forEach(l -> triangle.addElements((List)l));

        System.out.println(triangle.findMinimalPath());
    }
}

