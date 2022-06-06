package inheritance.analyser;

import java.util.List;

public class DifferentiatedTaxSalesAnalyser extends AbstractSalesAnalyser{

    protected Double taxRate;

    protected DifferentiatedTaxSalesAnalyser(List<SalesRecord> records) {
        super(records);
        this.taxRate = 1.2;
    }

    @Override
    protected Double getTotalSales() {
        double sales = 0.0;

        for (SalesRecord record:records){
            if (record.hasReducedRate()){
                taxRate = 1.1;
            }
            sales += (record.getItemsSold() * record.getProductPrice()) / taxRate;
        }
        return sales;
    }

    @Override
    protected Double getTotalSalesByProductId(String id) {
        double sales = 0.0;

        for (SalesRecord record:records){
            if (record.getProductId().equals(id)) {
                if (record.hasReducedRate()){
                    taxRate = 1.1;
                }
                sales += (record.getItemsSold() * record.getProductPrice()) / taxRate;
            }
        }
        return sales;
    }


}
