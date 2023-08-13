/**
 * SYST 17796 Project code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 *
 * @author Group2 : Mann Mehta, Maharsh Patel, Shlok Zala, Vaishnavi Barot
 */
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;
    private int score;  // Adding a score attribute
    
    public Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
        score = 0;  // Initializing score to 0
    }
    
    // Getter for player's name
    public String getName() {
        return name;
    }
    
    // Add a card to the player's hand
    public void addCardToHand(Card card) {
        hand.add(card);
    }
    
    // Check if the player has a card of a specific rank
    public boolean hasCardOfRank(String rank) {
        for (Card card : hand) {
            if (card.getRank().equals(rank)) {
                return true;
            }
        }
        return false;
    }
    
    // Give all cards of a specific rank from the player's hand
    public List<Card> giveCardsOfRank(String rank) {
        List<Card> matchingCards = new ArrayList<>();
        for (Card card : hand) {
            if (card.getRank().equals(rank)) {
                matchingCards.add(card);
            }
        }
        hand.removeAll(matchingCards);
        return matchingCards;
    }
    
    // Get the player's current hand
    public List<Card> getHand() {
        return hand;
    }
    
    // Display the player's hand
    public void displayHand() {
        System.out.println(name + "'s hand:");
        for (Card card : hand) {
            System.out.println(card);
        }
    }
    
    // Draw a card from a deck and add it to the player's hand
    public void drawCardFromDeck(GroupOfCards groupOfCards) {
        Card drawnCard = groupOfCards.drawCard();
        if (drawnCard != null) {
            addCardToHand(drawnCard);
        } else {
            System.out.println("The deck is empty.");
        }
    }
    
    // Check if the player's hand is empty
    public boolean isHandEmpty() {
        return hand.isEmpty();
    }
    
    // Add cards to the player's hand when drawing from the deck
    public void addCardsToHand(List<Card> cards) {
        hand.addAll(cards);
    }
    
    // Scoring related methods
    // Increase the player's score
    public void increaseScore(int points) {
        score += points;
    }
    
    // Get the player's current score
    public int getScore() {
        return score;
    }
    
    // Calculate and return the number of sets the player has collected
    public int calculateSets() {
        int[] rankCounts = new int[Card.RANKS.length];
        
        for (Card card : hand) {
            int rankIndex = getRankIndex(card.getRank()); // Use the helper method to get the rank index
            rankCounts[rankIndex]++;
        }
        
        int sets = 0;
        for (int count : rankCounts) {
            sets += count / 4;
        }
        
        return sets;
    }
    
    // Helper method to get the index of a rank in the Card.RANKS array
    private int getRankIndex(String rank) {
        for (int i = 0; i < Card.RANKS.length; i++) {
            if (Card.RANKS[i].equals(rank)) {
                return i;
            }
        }
        return -1; // Rank not found
    }
    
    // Display the player's score
    public void displayScore() {
        System.out.println(name + "'s score: " + score);
    }
    
    @Override
    public String toString() {
        return name + "'s hand: " + hand + " (Score: " + score + ")";
    }
}