package junit.sales;

import org.junit.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TopSalesFinderTests {

    @Test
    public void findTopSales() {

        SalesRecord[] records = {
                new SalesRecord("p1", 10, 1),
                new SalesRecord("p2", 20, 1),
                new SalesRecord("p2", 20, 1),
                new SalesRecord("p1", 10, 2),
                new SalesRecord("p3", 50, 1)
        };

        TopSalesFinder tsf = new TopSalesFinder();

        assertThat(tsf.findItemsSoldOver(10, records),
                arrayContainingInAnyOrder("p1", "p2", "p3"));

        assertThat(tsf.findItemsSoldOver(39, records),
                arrayContainingInAnyOrder("p2", "p3"));

        assertThat(tsf.findItemsSoldOver(40, records),
                arrayContainingInAnyOrder("p3"));

        assertThat(tsf.findItemsSoldOver(50, records),
                emptyArray());
    }

    @Test
    public void findTopSalesFromGeneratedData() {
        SalesRecord[] records = generateRecords(10, 10);

        TopSalesFinder tsf = new TopSalesFinder();

        assertThat(tsf.findItemsSoldOver(100, records),
                arrayContainingInAnyOrder("p1", "p3", "p4"));
    }

    @Test
    public void findTopSalesFromMoreGeneratedData() {
        SalesRecord[] records = generateRecords(100, 20);

        TopSalesFinder tsf = new TopSalesFinder();

        assertThat("There should be 19 items that sold over 100",
                tsf.findItemsSoldOver(100, records).length, is(19));

        assertThat("There should be 5 items that sold over 700",
                tsf.findItemsSoldOver(700, records).length, is(5));

        assertThat("Items sold over 700 are not correct",
                tsf.findItemsSoldOver(700, records),
                arrayContainingInAnyOrder("p5", "p6", "p11", "p14", "p15"));
    }

    @Test
    public void solutionIsNotTooInefficient() {
        TopSalesFinder tsf = new TopSalesFinder();

        tsf.findItemsSoldOver(100, generateRecords(500_000, 100));
    }

    private SalesRecord[] generateRecords(int recordCount, int differentProductCount) {
        SalesRecord[] records = new SalesRecord[recordCount];

        Random rand = new Random(0);
        for (int i = 0; i < recordCount; i++) {
            String id = "p" + rand.nextInt(differentProductCount);
            int price = rand.nextInt(50) + 1;
            int quantity = rand.nextInt(8) + 1;

            records[i] = new SalesRecord(id, price, quantity);
        }
        return records;
    }
}
