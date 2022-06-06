package collections.simulator;

import java.util.Objects;

public class Card implements Comparable<Card> {

    public enum CardValue { S2, S3, S4, S5, S6, S7, S8, S9, S10, J, Q, K, A }

    public enum CardSuit { C, D, H, S }

    private final CardValue value;
    private final CardSuit suit;

    public Card(CardValue value, CardSuit suit) {
        this.value = value;
        this.suit = suit;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Card)) {
            return false;
        }

        Card other = (Card) obj;

        return Objects.equals(value, other.value)
                && Objects.equals(suit, other.suit);
    }

    @Override
    public int compareTo(Card other) {
        if (value == other.value){
            return 0;
        } else if (value.ordinal() < other.value.ordinal()){
            return -1;
        } else {
            return 1;
        }
    }

    public CardValue getValue() {
        return value;
    }

    public CardSuit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", value, suit);
    }
}
