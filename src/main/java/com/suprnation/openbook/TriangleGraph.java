package com.suprnation.openbook;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.suprnation.openbook.ErrorMessage.NO_DATA_AVAILABLE_ERROR;
import static com.suprnation.openbook.ErrorMessage.SYSTEM_ERROR;

/**
 * Created by gandreou on 02/08/2021.
 */
public class TriangleGraph {
    Logger logger = LoggerFactory.getLogger(TriangleGraph.class);
    private List<Node> latestParents = new ArrayList<>();

    private BiFunction<List<Node>,Comparator<Integer>, List<Node>> extractComparables = ((list, comp) -> {
        List<Node> out = new ArrayList();
        if(list.size() > 1) {
            for (int i = 0; i < list.size()-1; i++) {
                if(comp.compare(list.get(i).getTrianglePathValue(), list.get(i+1).getTrianglePathValue()) == -1)
                    out.add(list.get(i));
                else
                    out.add(list.get(i+1));
            }
            return out;
        }
        return list;
    });

    private Predicate<List> isNotEmpty = (list) -> {
        if(list.isEmpty()) {
            logger.error("{}",NO_DATA_AVAILABLE_ERROR.getMessage());
            System.exit(1);
            return false;
        }
        return true;
    };

    private BiFunction <Node, String, String> createOutput = (n, s) ->
            s + " " + n.getTrianglePath() + " = " + n.getTrianglePathValue();

    private void addElements(List elements, Comparator comp) {
        if(isNotEmpty.test(elements))
            latestParents = buildTreeLayer.apply(elements, latestParents);
        latestParents = extractComparables.apply(latestParents, comp);
    }

    public void addElementsForMinimumPath(List elements) {
        Comparator<Integer> comp = Comparator.naturalOrder();
        this.addElements(elements, comp);
    }

    public void addElementsForMaximumPath(List elements) {
        Comparator<Integer> comp = Comparator.reverseOrder();
        this.addElements(elements, comp);
    }

    BiFunction<List<Integer>,List<Node>,List<Node>> buildTreeLayer =
            (elements, latestParents) -> {
                List<Node> parents = new ArrayList<>();

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

                logger.info("latestParents(#{}",parents.size());
                return parents;
            };

    public String getMinPath() {
        String output = null;
        if(latestParents.size() == 1) {
            output = createOutput.apply(latestParents.get(0), "Minimal path is:");
        } else {
            logger.error("{}", SYSTEM_ERROR.getMessage());
            System.exit(1);
        }
        return output;
    }

    public String getMaxPath() {
        String output = null;
        if(latestParents.size() == 1) {
            output = createOutput.apply(latestParents.get(0), "Miximal path is:");
        } else {
            logger.error("{}", SYSTEM_ERROR.getMessage());
            System.exit(1);
        }
        return output;
    }
}

