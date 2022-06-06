package fp;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Numbers {

    private List<Integer> numbers = Arrays.asList(1, 3, 4, 51, 24, 5);

    @Test
    public void findsOddNumbers() {

        List<Integer> oddNumbers = numbers.stream()
                .filter(x -> x % 2 == 1)
                .collect(Collectors.toList());

        System.out.println(oddNumbers);
    }

    @Test
    public void findsOddNumbersOver10() {
        List<Integer> oddNumbers = numbers.stream()
                .filter(x -> x % 2 == 1)
                .filter(x -> x > 10)
                .collect(Collectors.toList());

        System.out.println(oddNumbers);
    }

    @Test
    public void findsSquaredOddNumbers() {
        List<Integer> result = numbers.stream()
                .filter(x -> x % 2 == 1)
                .map(x -> x * x)
                .collect(Collectors.toList());

        System.out.println(result);
    }

    @Test
    public void findsSumOfOddNumbers() {
        Integer result = numbers.stream()
                .filter(x -> x % 2 == 1)
                .mapToInt(x -> x)
                .sum();

        System.out.println(result);
    }

}
