package com.suprnation.openbook;

import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private final static String SPLIT_REGEX = " ";

    private static final List<List<Integer>> lines = new ArrayList<>();

    public App() {}

    public static void main(String[] args) {

        TriangleGraph triangle = new TriangleGraph();
        int c = 0;
        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            logger.info("read line: {}", ++c);
            lines.add(Arrays.stream(s.nextLine().split(SPLIT_REGEX))
                    .map(Integer::parseInt).collect(Collectors.toList()));
        }
        Collections.reverse(lines);
        lines.forEach(triangle::addElementsForMinimumPath);
        System.out.println(triangle.getMinPath());
    }
}

