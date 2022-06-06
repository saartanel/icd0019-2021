package inheritance.analyser;

import java.util.List;

public class TaxFreeSalesAnalyser extends AbstractSalesAnalyser{

    protected TaxFreeSalesAnalyser(List<SalesRecord> records) {
        super(records);
    }

    @Override
    protected Double getTotalSales() {
        double sales = 0.0;

        for (SalesRecord record:records){
            sales += record.getItemsSold() * record.getProductPrice();
        }
        return sales;
    }

    @Override
    protected Double getTotalSalesByProductId(String id) {
        double sales = 0.0;

        for (SalesRecord record:records){
            if (record.getProductId().equals(id)) {
                sales += record.getItemsSold() * record.getProductPrice();
            }
        }
        return sales;
    }

}
