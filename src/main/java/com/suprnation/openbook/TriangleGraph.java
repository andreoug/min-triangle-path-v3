package com.suprnation.openbook;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.suprnation.openbook.ErrorMessage.NO_DATA_AVAILABLE_ERROR;
import static com.suprnation.openbook.ErrorMessage.SYSTEM_ERROR;

/**
 * Created by gandreou on 02/08/2021.
 */
public class TriangleGraph {
    private List<Node> latestParents = new ArrayList<>();

    private Function<List<Integer>,List<Integer>> findMins = (list -> {
        if(list.size() >1) {
            for (int i = list.size() - 1; i > 0; i--) {
                list.set(i - 1, Math.min(list.get(i - 1), list.get(i)));
            }
            list.remove(list.size() - 1);
        }
        return list;
    });

    public Function<Node,String> printMinimalPath = (n -> {
            return "Minimal path is: "
                    + n.getTrianglePath() + " = "
                    + n.getTrianglePathValue();
    });

    public void addElements(List elements) {
        if(elements.size() == 0) {
            System.out.println(NO_DATA_AVAILABLE_ERROR.getMessage());
            System.exit(1);
        } else {
            latestParents = buildTreeLayer.apply(elements, latestParents);
        }
    }

    BiFunction<List<Integer>,List<Node>,List<Node>> buildTreeLayer =
            (elements, latestParents) -> {
                List<Node> parents = new ArrayList<>();

                elements = findMins.apply(elements);

                if (latestParents.size() == 0) {
                    elements.stream().forEach(e -> {
                        parents.add(new Node(e.intValue(), String.valueOf(e.intValue()), e.intValue()));
                    });
                } else {
                    AtomicInteger atom = new AtomicInteger(0);
                    List<Node> finalLatestParents = latestParents;
                    elements.stream().forEach(e -> {
                        int val = e.intValue();
                        parents.add(new Node(val,
                                String.valueOf(val) + " + "
                                        + finalLatestParents.get(atom.intValue()).getTrianglePath(),
                                val + finalLatestParents.get(atom.getAndIncrement()).getTrianglePathValue()));
                    });
                }

                System.out.println("latestParents(#"+parents.size()+"): ");// + latestChildren);
                return parents;
            };

    public String findMinimalPath() {
        String output = null;
        if(latestParents.size() == 1) {
            output = printMinimalPath.apply(latestParents.get(0));
        } else {
            System.out.println(SYSTEM_ERROR.getMessage());
            System.exit(1);
        }

        return output;
    }
}

