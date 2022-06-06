package junit;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class Tests {

    @Test
    public void equalityExamples() {
        assertTrue(1 == 1);
        Integer x2 = 21;
        Integer y2 = 21;
        assertTrue(x2 == y2);
    }

    @Test
    public void assertThatAndAssertEqualsExample() {
        assertThat(1 + 2, is(3));

        assertThat(1 + 2, not(4));

        assertThat(new int[] {1, 2, 3}, is(new int[] {1, 2, 3}));
    }

    @Test
    public void findsSpecialNumbers() {
        assertTrue(Code.isSpecial(0));
        assertTrue(Code.isSpecial(1));
        assertTrue(Code.isSpecial(2));
        assertTrue(Code.isSpecial(3));
        assertFalse(Code.isSpecial(4));

        // other test cases for isSpecial() method
    }

    @Test
    public void findsLongestStreak() {
        assertThat(Code.longestStreak("aaaaakgwjegojwnoisandfosinaasfodsnvnvdsdsdfssfffsfssdfssssssfsdfewrwwwwwwwwwwrerermvvvvvasd"), is(10));

        // other test cases for longestStreak() method
    }

    @Test
    public void findsModeFromCharactersInString() {

        assertThat(Code.mode("abcb"), is('b'));

        assertThat(Code.mode(""), is(nullValue()));

        assertThat(Code.mode("abcbd"), is('b'));

        assertThat(Code.mode("cbbc"), is('c'));

        // other test cases for mode() method
    }




    @Test
    public void removesDuplicates() {
        assertThat(Code.removeDuplicates(arrayOf(1, 1)), is(arrayOf(1)));

        assertThat(Code.removeDuplicates(arrayOf(1, 2, 1, 3, 2)), is(arrayOf(1, 2, 3)));

        assertThat(Code.removeDuplicates(arrayOf(1, 2, 3)), is(arrayOf(1, 2, 3)));
    }

    @Test
    public void sumsIgnoringDuplicates() {
        assertThat(Code.sumIgnoringDuplicates(arrayOf(1, 1)), is(1));

        assertThat(Code.sumIgnoringDuplicates(arrayOf(1, 2, 1, 3, 2)), is(6));

        assertThat(Code.sumIgnoringDuplicates(arrayOf(1, 2, 3)), is(6));
    }

    private int[] arrayOf(int... numbers) {
        return numbers;
    }

}
