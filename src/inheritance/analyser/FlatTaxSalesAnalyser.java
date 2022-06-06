package inheritance.analyser;

import java.util.List;

public class FlatTaxSalesAnalyser extends AbstractSalesAnalyser {

    protected Double taxRate;


    protected FlatTaxSalesAnalyser(List<SalesRecord> records) {
        super(records);
        this.taxRate = 1.2;
    }

    @Override
    protected Double getTotalSales() {
        double sales = 0.0;

        for (SalesRecord record:records){
            sales += record.getItemsSold() * record.getProductPrice();
        }
        return sales / taxRate;
    }

    @Override
    protected Double getTotalSalesByProductId(String id) {
        double sales = 0.0;

        for (SalesRecord record:records){
            if (record.getProductId().equals(id)) {
                sales += record.getItemsSold() * record.getProductPrice();
            }
        }
        return sales / taxRate;
    }

}
