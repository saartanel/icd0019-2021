package types;

public class Code {

    public static void main(String[] args) {

        int[] numbers = {1, 3, -2, 9};

        System.out.println(squareDigits("a123sda")); // 11
    }

    public static int sum(int[] numbers) {
        Integer sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }

    public static double average(int[] numbers) {
        double sum = 0.0;
        for (int number : numbers) {
            sum += number;
        }
        return sum / numbers.length;
    }

    public static Integer minimumElement(int[] integers) {
        if (integers.length == 0) {
            return null;
        }
        Integer min = integers[0];

        for (int each : integers) {

            if (each < min) {
                min = each;
            }

        }
        return min;
    }

    public static String asString(int[] elements) {
        String result = "";

        for (int i = 0; i < elements.length; i++) {
            if (i == elements.length - 1) {
                result += elements[i];
            }
            else {
                result += elements[i] + ", ";
            }
        }
        return result;
    }

    public static String squareDigits(String s) {
        String out = "";
        char[] str = s.toCharArray();
        for (char c : str) {
            if (Character.isDigit(c)) {
                String s1 = Character.toString(c);
                int i = Integer.parseInt(s1);
                out += i * i;
            }
            else {
                out += c;
            }
        }
        return out;
    }
}
