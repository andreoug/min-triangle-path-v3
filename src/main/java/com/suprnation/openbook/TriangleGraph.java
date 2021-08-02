package com.suprnation.openbook;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.suprnation.openbook.ErrorMessage.NO_DATA_AVAILABLE_ERROR;
import static com.suprnation.openbook.ErrorMessage.SYSTEM_ERROR;

/**
 * Created by gandreou on 02/08/2021.
 */
public class TriangleGraph {
    private List<Node> latestChildren = new ArrayList<>();

    public void addElements(String[] elements) {
        if(elements.length == 0) {
            System.out.println(NO_DATA_AVAILABLE_ERROR.getMessage());
            System.exit(1);
        } else if(latestChildren.size() == 0 && elements.length == 1) {
            latestChildren.add(new Node(null, Integer.valueOf(elements[0])));
        } else {
            buildTreeLayer(elements);
        }
    }

    private void buildTreeLayer(String[] elements) {
        List<Node> children = new ArrayList<>();

//        System.out.println("#~#: elements.length: " + elements.length + ", latestChildren.size(): " + latestChildren.size());

        AtomicInteger c = new AtomicInteger();

        Map<Integer, List<Node>> groupByValueMap =
                latestChildren.stream()
                        .collect(Collectors.groupingBy(Node::getValue,
                                LinkedHashMap::new,
                                Collectors.toList()));

        groupByValueMap.forEach( (k,v) -> {
//            System.out.println(k + ": "+ v);
            v.stream().forEach(n -> {
//                System.out.println("current children size: " + children.size()+ ", elements.length:" + elements.length);
                children.add(new Node(n, Integer.valueOf(elements[c.get()])));
                if(c.get()+1< elements.length)
                    children.add(new Node(n, Integer.valueOf(elements[c.get() + 1])));
            });
            c.getAndIncrement();
        });
        groupByValueMap = null;

        latestChildren = children;
        latestChildren.stream()
//                .parallel()
                .forEach(Node::getTrianglePathValue);
        System.out.println("latestChildren(#"+latestChildren.size()+"): ");// + latestChildren);
    }

    public String findMinimalPath() {
        Optional<Node> node = latestChildren.stream()
//            .parallel()
                .collect(Collectors.minBy(Comparator.comparing(Node::getTrianglePathValue)));

        String output = null;
        if(node != null) {
            output = "Minimal path is: ";
            output += node.get().getTrianglePath() + " = ";
            output += node.get().getTrianglePathValue();
            System.out.println(output);
        } else {
            System.out.println(SYSTEM_ERROR.getMessage());
            System.exit(1);
        }

        return output;
    }
}

