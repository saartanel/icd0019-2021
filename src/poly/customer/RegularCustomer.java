package poly.customer;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RegularCustomer extends AbstractCustomer {

    private LocalDate lastOrderDate;

    public RegularCustomer(String id, String name,
                           int bonusPoints, LocalDate lastOrderDate) {

        super(id, name, bonusPoints);

            this.lastOrderDate = lastOrderDate;
    }

    @Override
    public void collectBonusPointsFrom(Order order) {
        Double total = order.getTotal();
        if (total.intValue() >= 100){
            // used this -> https://www.baeldung.com/java-date-difference
            long diff = lastOrderDate.until(order.getDate(), ChronoUnit.DAYS);
            if (diff < 30) {
                bonusPoints += order.getTotal() * 1.5;
            } else {
                bonusPoints += order.getTotal();
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        RegularCustomer other = (RegularCustomer) obj;

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
        out.add("REGULAR");
        out.add(id);
        out.add(name);
        out.add(String.valueOf(bonusPoints));
        if (lastOrderDate != null){
            out.add(lastOrderDate.toString());
        }
        return String.join(";", out);
    }

}