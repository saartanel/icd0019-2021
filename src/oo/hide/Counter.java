package oo.hide;

public class Counter {

    private int start;
    private int step;

    public Counter(int start, int step) {
        this.start = start;
        this.step = step;
    }

    public int nextValue() {
        int result = start;
        start += step;

        return result;
    }
}
