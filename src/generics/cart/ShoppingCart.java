package generics.cart;

import java.util.*;

public class ShoppingCart<T extends CartItem> {

    Map<T, Integer> cart = new HashMap<>();

    List<Double> discountList = new ArrayList<>();

    public void add(T item) {
        for (Map.Entry<T, Integer> product : cart.entrySet()) {
            if (product.getKey().getId().equals(item.getId())){
                increaseQuantity(item.getId());
                return;
            }
        }
        cart.put(item, 1);
    }

    public void removeById(String id) {
        for (Map.Entry<T, Integer> product: cart.entrySet()){
            if (product.getKey().getId().equals(id)){
                cart.remove(product.getKey());
                break;
            }
        }
    }

    public Double getTotal() {
        double totalPrice = 0.0;
        for (Map.Entry<T, Integer> product: cart.entrySet()){
            totalPrice += product.getKey().getPrice() * product.getValue();
        }
        for (Double discount : discountList){
            double discountMultiplier = discount / 100;
            totalPrice -= totalPrice * discountMultiplier;
        }
        return totalPrice;
    }

    public void increaseQuantity(String id) {
        for (Map.Entry<T, Integer> product: cart.entrySet()){
            if (product.getKey().getId().equals(id)){
                cart.put(product.getKey(), product.getValue() + 1);
            }
        }
    }

    public void applyDiscountPercentage(Double discount) {
        discountList.add(discount);
    }

    public void removeLastDiscount() {
        discountList.remove(discountList.size() - 1);
    }

    public void addAll(List<T> items) {
        for (T item : items){
            add(item);
        }
    }

    @Override
    public String toString(){
        List<String> result = new ArrayList<>();
        for (Map.Entry<T, Integer> product: cart.entrySet()){
            String identifier = product.getKey().getId();
            String price = product.getKey().getPrice().toString();
            String quantity = product.getValue().toString();
            result.add("(" + String.join(", ", identifier, price, quantity) + ")");
        }
        Collections.sort(result);
        return String.join(", ", result);
    }

}
