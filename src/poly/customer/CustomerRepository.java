package poly.customer;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

public class CustomerRepository {

    private static final String FILE_PATH = "src/poly/customer/data.txt";

    List<AbstractCustomer> customerList = buildRepository();

    public List<AbstractCustomer> buildRepository(){
        List<String> lines;

        try {
            lines = Files.readAllLines(Path.of(FILE_PATH));

        } catch (IOException e) {
            throw new RuntimeException("unable to read from file");
        }

        List<AbstractCustomer> customerList = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(";");

            String customerType = parts[0];
            String customerId = parts[1];
            String customerName = parts[2];
            int bonusPoints = Integer.parseInt(parts[3]);
            LocalDate lastOrderDate;

            try {
                lastOrderDate = LocalDate.parse(parts[4]);
            } catch (IndexOutOfBoundsException e) {
                lastOrderDate = null;
            }

            if ("REGULAR".equals(customerType)){
                customerList.add( new RegularCustomer(customerId, customerName, bonusPoints, lastOrderDate));
            } else if ("GOLD".equals(customerType)){
                customerList.add(new GoldCustomer(customerId, customerName, bonusPoints, lastOrderDate));
            }
        }
        return customerList;
    }

    public Optional<AbstractCustomer> getCustomerById(String id) {
        for (AbstractCustomer customer : customerList) {
            if (customer.getId().equals(id)){
                return Optional.of(customer);
            }
        }
        return Optional.empty();
    }

    public void remove(String id) {
        customerList.removeIf(customer -> customer.getId().equals(id));
    }

    public void save(AbstractCustomer customer) {
        try {
            customerList.removeIf(abstractCustomer -> abstractCustomer.getId().equals(customer.id));
            customerList.add(customer);

            FileWriter myWriter = new FileWriter(FILE_PATH, false);
            for (AbstractCustomer abstractCustomer : customerList) {
                myWriter.write(abstractCustomer.asString());
                myWriter.write("\n");
            }
            myWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("unable to write to file");
        }
    }

    public int getCustomerCount() {
        return customerList.size();
    }
}
