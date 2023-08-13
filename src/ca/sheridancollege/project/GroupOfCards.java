/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.Collections;

/**
 * A concrete class that represents any grouping of cards for a Game. HINT, you might want to subclass this more than
 * once. The group of cards has a maximum size attribute which is flexible for reuse.
 *
 * @author Group2 : Mann Mehta, Maharsh Patel, Shlok Zala, Vaishnavi Barot
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupOfCards {
    private List<Card> cards;

    public GroupOfCards() {
        cards = new ArrayList<>();
        initializeGroupOfCards();
        shuffle();
    }

    // Initialize the GroupOfCards with all 52 cards
    private void initializeGroupOfCards() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    // Shuffle the GroupOfCards using Fisher-Yates algorithm
    private void shuffle() {
        Random random = new Random();
        for (int i = cards.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    // Draw a card from the GroupOfCards, removing it
    public Card drawCard() {
        if (cards.isEmpty()) {
            return null; // No cards left
        }
        return cards.remove(0); // Remove and return the top card
    }

    // Get the number of cards left in the GroupOfCards
    public int size() {
        return cards.size();
    }
}
