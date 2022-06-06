package fp.examples;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FlattenListOfLists {

    @Test
    public void flatMapExample() {
        List<Integer> flattened = IntStream.range(0, 2)
                .mapToObj(pageNumber -> getPage(pageNumber, 3))
                .flatMap(page -> page.stream())
                .collect(Collectors.toList());

        assertThat(flattened, is(List.of(0, 1, 2, 3, 4, 5)));
    }

    private List<Integer> getPage(int pageNo, int pageSize) {
        List<Integer> result = new ArrayList<>();
        int start = pageNo * pageSize;
        int end = start + pageSize;
        for (int i = start; i < end; i++) {
            result.add(i);
        }
        return result;
    }

}


