package inheritance.analyser;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;

public abstract class AbstractSalesAnalyser {
    protected List<SalesRecord> records;

    protected AbstractSalesAnalyser(List<SalesRecord> records) {
        this.records = records;
    }

    protected abstract Double getTotalSales();

    protected abstract Double getTotalSalesByProductId(String id);

    protected String getIdOfMostPopularItem() {
        Map<String, Integer> itemList = new HashMap<>();

        for (SalesRecord record : records) {
            if (!itemList.containsKey(record.getProductId())) {
                itemList.put(record.getProductId(), record.getItemsSold());
            } else {
                itemList.put(record.getProductId(), itemList.get(record.getProductId()) + record.getItemsSold());
            }
        }

        return getMax(itemList);
    }

    protected String getIdOfItemWithLargestTotalSales() {
        Map<String, Integer> itemList = new HashMap<>();

        for (SalesRecord record : records) {
            if (!itemList.containsKey(record.getProductId())) {
                itemList.put(record.getProductId(), record.getItemsSold() * record.getProductPrice());
            } else {
                itemList.put(record.getProductId(), itemList.get(record.getProductId()) + (record.getItemsSold() * record.getProductPrice()));
            }
        }

        return getMax(itemList);
    }

    private String getMax(Map<String, Integer> itemList) {
        Integer mostSoldValue = 0;
        String mostSold = "";
        Set<String> keys = itemList.keySet();

        for (String key : keys) {
            if (itemList.get(key) > mostSoldValue) {
                mostSoldValue = itemList.get(key);
                mostSold = key;
            }
        }
        return mostSold;
    }
}
