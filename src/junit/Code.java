package junit;

public class Code {

    public static boolean isSpecial(int number) {
        return number % 11 < 4;
    }

    public static int longestStreak(String input) {
        if (input == null) {
            return 0;
        }
        int longest = 0;
        int curstreak = 1;
        Character lastChar = null;
        for (Character currentChar : input.toCharArray()) {
            if (currentChar.equals(lastChar)) {
                curstreak ++;
            } else {
                curstreak = 1;
            }
            if (curstreak > longest) {
                longest = curstreak;
            }
            lastChar = currentChar;
        }
        return longest;
    }

    public static Character mode(String input) {
        if (input == null) {
            return null;
        }

        int modeCount = 0;
        Character mode = null;
        for (char c1 : input.toCharArray()) {
            int count = getCharacterCount(input, c1);
            if (count > modeCount) {
                modeCount = count;
                mode = c1;
            }
        }
        return mode;
    }

    public static int getCharacterCount(String input, char c1) {
        int count = 0;
        for (char c2 : input.toCharArray()) {
            if (c2 == c1) {
                count++;
            }
        }
        return count;
    }

    public static int[] removeDuplicates(int[] input) {
        if (input.length == 1 || input.length == 0) {
            return input;
        }

        int[] newArr = new int[input.length];
        int newArrIndex = 0;
        boolean zero = false;
        for (int j : input) {
            if (!zero && j == 0) {
                newArr[newArrIndex] = j;
                newArrIndex++;
                zero = true;
            }
            if (!checkIfDuplicate(newArr, j)) {
                newArr[newArrIndex] = j;
                newArrIndex++;
            }
        }
        int[] outputArray = new int[newArrIndex];
        for (int i = 0; i < newArrIndex; i++) {
            outputArray[i] = newArr[i];
        }
        return outputArray;
    }

    public static boolean checkIfDuplicate(int[] input, int nr) {
        for (int j : input) {
            if (nr == j) {
                return true;
            }
        }
        return false;
    }

    public static int sumIgnoringDuplicates(int[] integers) {
        int[] newArr = removeDuplicates(integers);

        int output = 0;

        for (int j : newArr) {
            output += j;
        }
        return output;
    }
}
