package fp.examples;

import org.junit.Test;

public class AssertThrows {

    @Test(expected = IllegalArgumentException.class)
    public void incorrectTestForErrorConditions() {
        first(new int[0]);

        first(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyArrayDoesNotHaveFirstElement() {
        first(new int[0]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullDoesNotHaveFirstElement() {
        first(null);
    }

    @Test
    public void testForErrorConditions() {
        assertThrows(() -> first(new int[0]), IllegalArgumentException.class);

        assertThrows(() -> first(null), IllegalArgumentException.class);
    }

    private void assertThrows(Runnable code, Class<? extends Exception> expected) {
        try {
            code.run();
        } catch (Exception actual) {
            if (actual.getClass() != expected) {
                throw new AssertionError("Unexpected exception: " + actual);
            }

            return;
        }

        throw new AssertionError("Should throw: " + expected);
    }

    private int first(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException();
        }
        return numbers[0];
    }

}
