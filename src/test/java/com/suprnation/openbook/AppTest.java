package com.suprnation.openbook;

/**
 * Created by gandreou on 02/08/2021.
 */
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private final static String SPLIT_REGEX = " ";

    /**
     * Rigorous Test.
     */

    @Test
    public void shouldReturnsMinTriangleValues() {

//        assertThat(triangle.findMinimalPath(), is("Minimal path is: 1 + 21 + 31 = 53"));
    }

    @Test
    public void shouldReturnSingleMinTriangleValue_emulateApp() {

        TriangleGraph triangle = new TriangleGraph();
        boolean success = true;

        String data =
                "31 32 33\n" +
                "21 22\n" +
                "1\n" +
                "";
        List<String> dataArray = Arrays.asList(data.split("\n"));
        for(String s: dataArray) {
            triangle.addElements(Arrays.stream(s.split(SPLIT_REGEX)).sequential().map(Integer::parseInt).collect(Collectors.toList()));
        }

        assertThat(triangle.findMinimalPath(), is("Minimal path is: 1 + 21 + 31 = 53"));
    }

    @Test
    public void shouldReturnSingleMinTriangleValueWithDiffNumbers_emulateApp() {

        TriangleGraph triangle = new TriangleGraph();
        boolean success = true;

        String data =
                "22 32 33\n" +
                "33 32\n" +
                "1\n" +
                "";
        List<String> dataArray = Arrays.asList(data.split("\n"));
        for(String s: dataArray) {
            triangle.addElements(Arrays.stream(s.split(SPLIT_REGEX)).sequential().map(Integer::parseInt).collect(Collectors.toList()));
        }

        assertThat(triangle.findMinimalPath(), is("Minimal path is: 1 + 32 + 22 = 55"));
    }

    @Test
    public void shouldReturnMultipleMinTriangleValuesForDifferectPaths_emulateApp() {

        TriangleGraph triangle = new TriangleGraph();
        boolean success = true;

        String data =
                "22 2 33\n" +
                "33 32\n" +
                "2\n" +
                "";
        List<String> dataArray = Arrays.asList(data.split("\n"));
        for(String s: dataArray) {
            triangle.addElements(Arrays.stream(s.split(SPLIT_REGEX)).sequential().map(Integer::parseInt).collect(Collectors.toList()));
        }

        assertThat(triangle.findMinimalPath(), is("Minimal path is: 2 + 32 + 2 = 36"));

    }
}
