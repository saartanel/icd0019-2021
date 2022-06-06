package junit.sales;

public class TopSalesFinder {

    int runCount = 0;
    int index = 0;
    String[] idList = new String[index];
    Integer[] priceList = new Integer[index];

    public String[] findItemsSoldOver(int amount, SalesRecord[] records) {
        if (runCount < 1) {
            calculatePriceList(records);
            runCount ++;
        }
        int length = 0;
        for (Integer integer : priceList) {
            if (integer > amount) {
                length ++;
            }
        }
        String[] output = new String[length];
        int index2 = 0;
        for (int i = 0; i < priceList.length; i++) {
            if (priceList[i] > amount) {
                output[index2++] = idList[i];
            }
        }
        return output;
    }

    private void calculatePriceList(SalesRecord[] records) {
        for (SalesRecord record : records) {
            if (!inList(record.getProductId())){
                String[] newIdList = new String[index + 1];
                Integer[] newPriceList = new Integer[index + 1];
                newIdList[index] = record.getProductId();
                newPriceList[index] = record.getProductPrice() * record.getItemsSold();
                index ++;
                idList = addToIdList(newIdList);
                priceList = addToPriceList(newPriceList);
            } else {
                priceList[getIndex(record.getProductId())] += record.getProductPrice() * record.getItemsSold();
            }
        }
    }

    private int getIndex(String id) {
        int index = 0;
        for (int i = 0; i < idList.length; i++) {
            if (idList[i].equals(id)){
                index = i;
            }
        }
        return index;
    }

    private String[] addToIdList(String[] newIdList) {
        for (int i = 0; i < idList.length; i++) {
            newIdList[i] = idList[i];
        }
        return newIdList;
    }

    private Integer[] addToPriceList(Integer[] newPriceList) {
        for (int i = 0; i < priceList.length; i++) {
            newPriceList[i] = priceList[i];
        }
        return newPriceList;
    }

    private boolean inList(String id) {
        for (String s : idList) {
            if (s.equals(id)) {
                return true;
            }
        }
        return false;
    }
}