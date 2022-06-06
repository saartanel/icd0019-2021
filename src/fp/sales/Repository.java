package fp.sales;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.*;
import java.util.*;
import java.util.stream.*;

public class Repository {

    private static final String FILE_PATH = "src/fp/sales/sales-data.csv";

    private DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    private List<String> getContent() {
        List<String> content;
        try {
            content = Files.readAllLines(Paths.get(FILE_PATH));
        } catch (IOException e) {
            throw new RuntimeException("fix file");
        }

        return content;
    }

    private List<Entry> createEntries(List<String> content){
        List<Entry> entries = new ArrayList<>();
        IntStream.range(1, content.size())
                .forEach(index -> {
                    Entry entry = new Entry();
                    List<String> row = new ArrayList<>(Arrays.asList(content.get(index).split("\t")));
                    entry.setDate(LocalDate.parse(row.get(0), formatter));
                    entry.setState(row.get(1));
                    entry.setProductId(row.get(2));
                    entry.setCategory(row.get(3));
                    entry.setAmount(Double.valueOf(row.get(5).replace(",", ".")));
                    entries.add(entry);
                });

        return entries;
    }

    public List<Entry> returnEntries(){
        List<String> content = getContent();
        return createEntries(content);
    }
}
