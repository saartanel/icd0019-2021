package collections.simulator;

import java.util.*;

public class Hand implements Iterable<Card> {

    private List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public String toString() {
        return cards.toString();
    }

    public HandType getHandType() {
        Collections.sort(cards);
        HandType type;
        if (cards.size() == 2){
            type = checkTwoCards(cards);
        } else if (cards.size() == 3) {
            type = checkThreeCards(cards);
        } else if (cards.size() == 4){
            type = checkFourCards(cards);
        } else if (cards.size() == 5){
            type = checkFiveCards(cards);
        } else {
            type = HandType.HIGH_CARD;
        }

        return type;
    }

    private HandType checkTwoCards(List<Card> cards) {
        HashMap<Card.CardValue, Integer> cardMap = createMap(cards);
        Integer maxValue = maximumValue(cardMap);

        if (maxValue == 2){
            return HandType.ONE_PAIR;
        } else {
            return HandType.HIGH_CARD;
        }
    }

    private HandType checkThreeCards(List<Card> cards) {
        HashMap<Card.CardValue, Integer> cardMap = createMap(cards);
        Integer maxValue = maximumValue(cardMap);

        if (maxValue == 3){
            return HandType.TRIPS;
        } else if (maximumValue(cardMap) == 2){
            return HandType.ONE_PAIR;
        } else {
            return HandType.HIGH_CARD;
        }
    }

    private HandType checkFourCards(List<Card> cards) {
        HashMap<Card.CardValue, Integer> cardMap = createMap(cards);
        Integer maxValue = maximumValue(cardMap);
        if (maxValue == 4){
            return HandType.FOUR_OF_A_KIND;
        } else if (maxValue == 3){
            return HandType.TRIPS;
        }else if (getTwoPairs(cardMap)){
            return HandType.TWO_PAIRS;
        } else if (maximumValue(cardMap) == 2){
            return HandType.ONE_PAIR;
        } else {
            return HandType.HIGH_CARD;
        }
    }

    private HandType checkFiveCards(List<Card> cards){
        HashMap<Card.CardValue, Integer> cardMap = createMap(cards);
        Integer maxValue = maximumValue(cardMap);
        if (isStraightFlush(cards)){
            return HandType.STRAIGHT_FLUSH;
        } else if (maxValue == 4){
            return HandType.FOUR_OF_A_KIND;
        } else if (isFullHouse(cards)){
            return HandType.FULL_HOUSE;
        } else if (isFlush(cards)) {
            return HandType.FLUSH;
        } else if (isStraight(cards)){
            return HandType.STRAIGHT;
        }  else if (maxValue == 3){
            return HandType.TRIPS;
        } else if (getTwoPairs(cardMap)){
            return HandType.TWO_PAIRS;
        } else if (maximumValue(cardMap) == 2){
            return HandType.ONE_PAIR;
        } else {
            return HandType.HIGH_CARD;
        }
    }

    private Integer maximumValue(HashMap<Card.CardValue, Integer> cardMap){

        Map.Entry<Card.CardValue, Integer> maxEntry = null;

        for (Map.Entry<Card.CardValue, Integer> entry : cardMap.entrySet()) {
            if (maxEntry == null || entry.getValue()
                    .compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }
        return maxEntry.getValue();
    }

    private HashMap<Card.CardValue, Integer> createMap(List<Card> cards){
        HashMap<Card.CardValue, Integer> cardMap = new HashMap<>();
        for (Card card:cards){
            if (cardMap.containsKey(card.getValue())) {
                cardMap.put(card.getValue(), cardMap.get(card.getValue()) + 1);
            } else {
                cardMap.put(card.getValue(), 1);
            }
        }
        return cardMap;
    }

    private boolean getTwoPairs(HashMap<Card.CardValue, Integer> cardMap){
        int count = 0;

        for (Card.CardValue key:cardMap.keySet()){
            if (cardMap.get(key) == 2){
                count ++;
            }
        }
        return count == 2;
    }

    private boolean isStraight(List<Card> cards){
        int value = cards.get(0).getValue().ordinal();

        for (int i = 1; i < 5; i++) {
            if (i == 4 && cards.get(i).getValue().ordinal() == 12 ){
                return true;
            }
            if (cards.get(i).getValue().ordinal() - value != 1){
                return false;
            }

            value = cards.get(i).getValue().ordinal();
        }
        return true;
    }

    private boolean isFlush(List<Card> cards){
        Card.CardSuit suit = cards.get(0).getSuit();

        for (Card card:cards){
            if (card.getSuit() != suit){
                return false;
            }
        }
        return true;
    }

    private boolean isFullHouse(List<Card> cards){
        HashMap<Card.CardValue, Integer> cardMap = createMap(cards);
        boolean containsTwo = false;
        boolean containsThree = false;

        for (Card.CardValue key:cardMap.keySet()){
            if (cardMap.get(key) == 2){
                containsTwo = true;
            } else if (cardMap.get(key) == 3){
                containsThree = true;
            }
        }
        return containsTwo && containsThree;

    }

    private boolean isStraightFlush(List<Card> cards){
        return isFlush(cards) && isStraight(cards);
    }

    public boolean contains(Card card) {
        return cards.contains(card);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }
}
