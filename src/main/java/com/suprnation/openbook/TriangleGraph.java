package com.suprnation.openbook;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.suprnation.openbook.ErrorMessage.NO_DATA_AVAILABLE_ERROR;
import static com.suprnation.openbook.ErrorMessage.SYSTEM_ERROR;

public class TriangleGraph {
    Logger logger = LoggerFactory.getLogger(TriangleGraph.class);
    private List<Node> parents = new ArrayList<>();

    private final BiFunction<List<Node>,Comparator<Integer>, List<Node>> extractComparables = ((list, comp) -> {
        List<Node> out = new ArrayList<>();
        if(list.size() > 1) {
            for (int i = 0; i < list.size()-1; i++) {
                if(comp.compare(list.get(i).getTrianglePathValue(), list.get(i + 1).getTrianglePathValue()) < 0)
                    out.add(list.get(i));
                else
                    out.add(list.get(i+1));
            }
            return out;
        }
        return list;
    });

    private final Predicate<List<Integer>> isNotEmpty = (list) -> {
        if(list.isEmpty()) {
            logger.error("{}",NO_DATA_AVAILABLE_ERROR.getMessage());
            System.exit(1);
            return false;
        }
        return true;
    };

    private final BiFunction <Node, String, String> createOutput = (n, s) ->
            s + " " + n.getTrianglePath() + " = " + n.getTrianglePathValue();

    private void addElements(List<Integer> elements, Comparator<Integer> comp) {
        if(isNotEmpty.test(elements))
            parents = buildTreeLayer.apply(elements, parents);
        parents = extractComparables.apply(parents, comp);
    }

    public void addElementsForMinimumPath(List<Integer> elements) {
        Comparator<Integer> comp = Comparator.naturalOrder();
        this.addElements(elements, comp);
    }

    public void addElementsForMaximumPath(List<Integer> elements) {
        Comparator<Integer> comp = Comparator.reverseOrder();
        this.addElements(elements, comp);
    }

    BiFunction<Integer,Node, Node> updateNode = (val, node) ->
            new Node(val, val + " + " + node.getTrianglePath(),
            node.getTrianglePathValue()+ val);

    BiFunction<List<Integer>,List<Node>,List<Node>> buildTreeLayer =
            (elements, parents) -> {
                if (parents.size() == 0) {
                    elements.forEach(e ->
                        parents.add(new Node(e, String.valueOf(e.intValue()), e)));
                } else {
                    IntStream.range(0,elements.size()).forEach( e ->
                        parents.set(e, updateNode.apply(elements.get(e),parents.get(e))));
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

