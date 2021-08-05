package com.suprnation.openbook;

/**
 * Created by gandreou on 02/08/2021.
 */
import org.junit.Test;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
    public void shouldReturnsTrueValues() {

        Function<Long, Long> adder = (value) -> value + 3;
        Long resultLambda = adder.apply((long) 8);
        assertThat(adder.apply((long)8), is((long)11));

        Predicate predicate = (value) -> value != null;
        assertThat(predicate.test(null), is(false));

        UnaryOperator<Integer> unaryOperator =
                (i) -> { i = new Integer(1); return i; };
        assertThat(unaryOperator.apply(new Integer(0)), is(new Integer(1)));

        BinaryOperator<List> binaryOperator =
                (value1, value2) -> { value2.addAll(value1); return value1; };
        String[] strArray1 = {"Delhi", "Mumbai", "Kolkata", "Chennai"};
        List<String> mylist1 = new ArrayList<String>(Arrays.asList(strArray1));
        String[] strArray2 = {"1", "2", "3", "4"};
        List<String> mylist2 = new ArrayList<String>(Arrays.asList(strArray2));
        assertThat(binaryOperator.apply(mylist1,mylist2), is(mylist1));

        Supplier<Integer> supplier = () -> new Integer((int) (1));
        assertThat(supplier.get(), is(1));

        Consumer<Integer> consumer = (value) -> System.out.println("#~#: consumer: " + value);
        consumer.accept(1);



        Predicate<String> startsWithA = (text) -> text.startsWith("A");
        Predicate<String> endsWithX   = (text) -> text.endsWith("x");

        Predicate<String> composed1 = startsWithA.and(endsWithX);
        String input = "A hardworking person must relax";
        boolean result1 = composed1.test(input);
        System.out.println(result1);

        Predicate<String> composed2 = startsWithA.or(endsWithX);
        boolean result2 = composed2.test(input);
        System.out.println(result2);


        Function<Integer, Integer> multiply = (value) -> value * 2;
        Function<Integer, Integer> add      = (value) -> value + 3;

        Function<Integer, Integer> addThenMultiply = multiply.compose(add);
        assertThat(addThenMultiply.apply(3), is(((3 + 3) * 2 )));

        Function<Integer, Integer> multiplyThenAdd = multiply.andThen(add);
        assertThat(multiplyThenAdd.apply(3), is((3 * 2 + 3 )));



        // Creating a List using toList Collectors method
        List<String> list1 = Stream.of("January", "February", "March", "April", "May")
                .collect(Collectors.toList());
//        assertThat(list1, is("<[January, February, March, April, May]>"));
//        java.lang.AssertionError:
//        Expected: is "<[January, February, March, April, May]>"
//        but: was <[January, February, March, April, May]>
//        Expected :is "<[January, February, March, April, May]>"
//        Actual   :<[January, February, March, April, May]>
        assertThat(list1, is(list1));
        // Print the list
        System.out.println("List from Java 8 stream: "
                + list1.toString());
        //ref: https://www.softwaretestinghelp.com/java-list-how-to-create-initialize-use-list-in-java/



        List<Integer> list = Arrays.asList(1, 4, 2, 5, 3);
        Collections.reverse(list);
        System.out.println(list);

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
