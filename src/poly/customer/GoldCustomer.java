package poly.customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GoldCustomer extends AbstractCustomer {

    private LocalDate lastOrderDate;

    public GoldCustomer(String id, String name,
                        int bonusPoints, LocalDate lastOrderDate) {

        super(id, name, bonusPoints);

        this.lastOrderDate = lastOrderDate;
    }

    @Override
    public void collectBonusPointsFrom(Order order) {
        if (order.getTotal() > 100) {
            bonusPoints += order.getTotal() * 1.5;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        GoldCustomer other = (GoldCustomer) obj;

        return Objects.equals(id, other.id) &&
                Objects.equals(name, other.name) &&
                Objects.equals(bonusPoints, other.bonusPoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bonusPoints);
    }

    @Override
    public String asString() {
        List<String> out = new ArrayList<>();
        out.add("GOLD");
        out.add(id);
        out.add(name);
        out.add(String.valueOf(bonusPoints));
        if (lastOrderDate != null){
            out.add(lastOrderDate.toString());
        }
        return String.join(";", out);
    }

}