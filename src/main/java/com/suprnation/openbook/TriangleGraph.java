package com.suprnation.openbook;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.suprnation.openbook.ErrorMessage.NO_DATA_AVAILABLE_ERROR;
import static com.suprnation.openbook.ErrorMessage.SYSTEM_ERROR;

/**
 * Created by gandreou on 02/08/2021.
 */
public class TriangleGraph {
    Logger logger = LoggerFactory.getLogger(TriangleGraph.class);
    private List<Node> parents = new ArrayList<>();

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

    private void addElements(List<Integer> elements, Comparator comp) {
        if(isNotEmpty.test(elements))
            parents = buildTreeLayer.apply(elements, parents);
        parents = extractComparables.apply(parents, comp);
    }

    public void addElementsForMinimumPath(List elements) {
        Comparator<Integer> comp = Comparator.naturalOrder();
        this.addElements(elements, comp);
    }

    public void addElementsForMaximumPath(List elements) {
        Comparator<Integer> comp = Comparator.reverseOrder();
        this.addElements(elements, comp);
    }

    BiFunction<Integer,Node, Node> updateNode = (val, node) -> {
        return new Node(val,
                String.valueOf(val.intValue()) + " + " + node.getTrianglePath(),
                node.getTrianglePathValue()+val.intValue());
    };

    BiFunction<List<Integer>,List<Node>,List<Node>> buildTreeLayer =
            (elements, parents) -> {
                if (parents.size() == 0) {
                    elements.stream().forEach(e -> {
                        parents.add(new Node(e.intValue(), String.valueOf(e.intValue()), e.intValue()));
                    });
                } else {
                    IntStream.range(0,elements.size()).forEach( e -> {
                        Node node = updateNode.apply(elements.get(e).intValue(),parents.get(e));
                        parents.set(e, node);
                    });
                }

                logger.info("parents(#{}",parents.size());
                return parents;
            };

    public String getMinPath() {
        String output = null;
        if(parents.size() == 1) {
            output = createOutput.apply(parents.get(0), "Minimal path is:");
        } else {
            logger.error("{}", SYSTEM_ERROR.getMessage());
            System.exit(1);
        }
        return output;
    }

    public String getMaxPath() {
        String output = null;
        if(parents.size() == 1) {
            output = createOutput.apply(parents.get(0), "Miximal path is:");
        } else {
            logger.error("{}", SYSTEM_ERROR.getMessage());
            System.exit(1);
        }
        return output;
    }
}

