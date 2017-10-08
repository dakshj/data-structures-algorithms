import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeckOfCards {

    public enum Rank {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING}

    public enum Suit {HEARTS, DIAMONDS, SPADES, CLUBS}

    public static class Card {
        private final Rank rank;
        private final Suit suit;

        private Card(final Rank rank, final Suit suit) {
            this.rank = rank;
            this.suit = suit;
        }

        @Override
        public String toString() {
            return rank + " of " + suit;
        }
    }

    private final List<Card> cards = new ArrayList<>();

    private DeckOfCards() {
        initCards();
    }

    private void initCards() {
        for (final Suit suit : Suit.values()) {
            for (final Rank rank : Rank.values()) {
                addCard(suit, rank);
            }
        }
    }

    private List<Card> getCards() {
        return cards;
    }

    private void addCard(final Suit suit, final Rank rank) {
        cards.add(new Card(rank, suit));
    }

    private void shuffleCards(final int swapCount) {
        for (int i = 0; i < swapCount; i++) {
            int randomIndexA = new Random().nextInt(cards.size());
            int randomIndexB = new Random().nextInt(cards.size());
            cards.add(randomIndexB, cards.remove(randomIndexA));
        }
    }

    public static void main(String[] args) {
        final DeckOfCards deck = new DeckOfCards();
        System.out.println(deck.getCards());
        deck.shuffleCards(10000);
        System.out.println(deck.getCards());
        deck.shuffleCards(8000);
        System.out.println(deck.getCards());
    }
}
