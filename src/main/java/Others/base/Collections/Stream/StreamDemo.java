package Others.base.Collections.Stream;

import Utills.PrintUtill;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1,2),
                Arrays.asList(2, 3,4),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.
                flatMap((childList) -> childList.stream());
        outputStream.forEach(p -> PrintUtill.println(p) );

        List<Integer> list = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        ).flatMap((childList) -> childList.stream()).distinct().collect(Collectors.toList());
        PrintUtill.println(list);

        int sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();

        PrintUtill.println("sumValue: "+sumValue);
    }
}
