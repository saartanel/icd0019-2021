package fp.sales;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Analyser {

    private  List<Entry> entries;
    private Repository repository;

    public Analyser(Repository repository) {
        this.repository = repository;
        this.entries = repository.returnEntries().stream().sorted(Collections.reverseOrder(Comparator.comparing(Entry::getAmount))).collect(Collectors.toList());
    }

    public Double getTotalSales() {
        return entries.stream().mapToDouble(Entry::getAmount).sum();
    }

    public Double getSalesByCategory(String category) {
        return entries.stream().filter(x -> x.getCategory().equals(category)).mapToDouble(Entry::getAmount).sum();
    }

    public Double getSalesBetween(LocalDate start, LocalDate end) {
        return entries.stream().filter(x -> x.getDate().isAfter(start)).filter(x -> x.getDate().isBefore(end)).mapToDouble(Entry::getAmount).sum();
    }

    public String mostExpensiveItems() {
        return entries.stream().limit(3).map(Entry::getProductId).sorted().collect(Collectors.joining(", "));
    }

    public String statesWithBiggestSales() {
        HashMap<String, Double> sales = new HashMap<>();

        IntStream.range(0, entries.size())
                .forEach(index -> {
                    Entry entry = entries.get(index);
                    if (entry.getAmount() != null){
                        if (sales.containsKey(entry.getState())){
                            sales.put(entry.getState(), sales.get(entry.getState()) + entry.getAmount());
                        } else {
                            sales.put(entry.getState(), entry.getAmount());
                        }
                    }
                });

        return sales.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).map(Map.Entry::getKey).limit(3).collect(Collectors.joining(", "));
    }
}
