package fp.examples;

import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Sorting {

    @Test
    public void sortingByNaturalOrder() {
        List<Integer> numbers = List.of(5, 2, 0, 3);

        List<Integer> sorted = numbers.stream()
                .sorted()
                .collect(Collectors.toList());

        assertThat(sorted, is(List.of(0, 2, 3, 5)));
    }

    @Test
    public void sortingByCustomCriteria() {
        List<Integer> numbers = List.of(1, 5, 2);

        List<Integer> sorted = numbers.stream()
                .sorted((a, b) -> b.compareTo(a))
                .collect(Collectors.toList());

        assertThat(sorted, is(List.of(5, 2, 1)));
    }

    @Test
    public void sortingByCustomCriteriaWithProvidedUtilities() {
        List<String> numbers = List.of("123", "1", "23");

        List<String> sorted = numbers.stream()
                .sorted(Collections.reverseOrder(Comparator.comparing(each -> each.length())))
                .collect(Collectors.toList());

        // Comparator.comparing() picks sorting criteria
        // wrapping it with Collections.reverseOrder reverses the order

        assertThat(sorted, is(List.of("123", "23", "1")));
    }

}


