package com.suprnation.openbook;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.suprnation.openbook.ErrorMessage.NO_DATA_AVAILABLE_ERROR;
import static com.suprnation.openbook.ErrorMessage.SYSTEM_ERROR;

/**
 * Created by gandreou on 02/08/2021.
 */
public class TriangleGraph {
    private List<Node> latestParents = new ArrayList<>();

    public void addElements(List elements) {
//        System.out.println("#~#: Lines: "+elements);
        if(elements.size() == 0) {
            System.out.println(NO_DATA_AVAILABLE_ERROR.getMessage());
            System.exit(1);
        } else {
            buildTreeLayer(elements);
        }
    }

    private void buildTreeLayer(List<Integer> elements) {
        List<Node> parents = new ArrayList<>();

//        System.out.println("#~#: elements.size: " + elements.size() + ", latestParent.size(): " + latestParents.size());

        if(elements.size() >1) {
            for (int i = elements.size() - 1; i > 0; i--) {
                elements.set(i - 1, Math.min(elements.get(i - 1), elements.get(i)));
            }

            elements.remove(elements.size() - 1);

            if (latestParents.size() == 0) {
                elements.stream().forEach(e -> {
                    parents.add(new Node(e.intValue(), String.valueOf(e.intValue()), e.intValue()));
                });
            } else {
                AtomicInteger atom = new AtomicInteger(0);
                elements.stream().forEach(e -> {
                    int val = e.intValue();
                    parents.add(new Node(val,
                            String.valueOf(val) + " + "
                                    + latestParents.get(atom.intValue()).getTrianglePath(),
                            val + latestParents.get(atom.getAndIncrement()).getTrianglePathValue()));
                });
            }
        } else {
            System.out.println("#~#: ROOT value: elements: " + elements);
            //TODO: include this 'else' step in the previous 'else' step
            parents.add(new Node(elements.get(0),
                    String.valueOf(elements.get(0)) + " + "
                            + latestParents.get(0).getTrianglePath(),
                    elements.get(0) + latestParents.get(0).getTrianglePathValue()));
        }

//        System.out.println("#~#: elements: [#"+elements.size()+"]" + elements);

        latestParents = parents;
        System.out.println("latestParents(#"+latestParents.size()+"): ");// + latestChildren);
//        System.out.println("latestParents(#"+latestParents.size()+"): " + latestParents);
    }

        public String findMinimalPath() {
        String output = null;
        if(latestParents.size() == 1) {
            output = "Minimal path is: ";
            output += latestParents.get(0).getTrianglePath() + " = ";
            output += latestParents.get(0).getTrianglePathValue();
            System.out.println(output);
        } else {
            System.out.println(SYSTEM_ERROR.getMessage());
            System.exit(1);
        }

        return output;
    }
}

