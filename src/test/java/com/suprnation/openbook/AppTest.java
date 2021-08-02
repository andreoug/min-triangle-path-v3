package com.suprnation.openbook;

/**
 * Created by gandreou on 02/08/2021.
 */
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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
    public void shouldReturnSingleMinTriangleValue_emulateApp() {

        TriangleGraph triangle = new TriangleGraph();
        boolean success = true;

        String data =
                "1\n" +
                        "21 22\n" +
                        "31 32 33\n" +
                        "";
        List<String> dataArray = Arrays.asList(data.split("\n"));
        for(String s: dataArray) {
            triangle.addElements(s.split(SPLIT_REGEX));
        }

        assertThat(triangle.findMinimalPath(), is("Minimal path is: 1 + 21 + 31 = 53"));
    }

    @Test
    public void shouldReturnSingleMinTriangleValueWithDiffNumbers_emulateApp() {

        TriangleGraph triangle = new TriangleGraph();
        boolean success = true;

        String data =
                "1\n" +
                        "33 32\n" +
                        "22 32 33\n" +
                        "";
        List<String> dataArray = Arrays.asList(data.split("\n"));
        for(String s: dataArray) {
            triangle.addElements(s.split(SPLIT_REGEX));
        }

        assertThat(triangle.findMinimalPath(), is("Minimal path is: 1 + 33 + 32 = 66"));
    }

    @Test
    public void shouldReturnMultipleMinTriangleValuesForDifferectPaths_emulateApp() {

        TriangleGraph triangle = new TriangleGraph();
        boolean success = true;

        String data =
                "1\n" +
                        "33 32\n" +
                        "22 2 33\n" +
                        "";
        List<String> dataArray = Arrays.asList(data.split("\n"));
        for(String s: dataArray) {
            triangle.addElements(s.split(SPLIT_REGEX));
        }

        assertThat(triangle.findMinimalPath(), is("Minimal path is: 1 + 32 + 2 = 35"));

    }
}
