package ca.sheridancollege.project;

/**
 * A class to be used as the Card class for the project. Must be general enough to be instantiated for any Card
 * game. Students wishing to add to the code should remember to add themselves as a modifier.
 * 
 * @author Group2: Mann Mehta, Maharsh Patel, Shlok Zala, Vaishnavi Barot
 * 
 */
// Card class represents a playing card.
public class Card {
    private String rank; // Rank of the card (e.g., Ace, 2, Queen)
    private String suit; // Suit of the card (e.g., Hearts, Spades)

    // Array of possible ranks
    public static final String[] RANKS = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

    // Constructor to initialize the card with rank and suit.
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    // Getter method to retrieve the rank of the card.
    public String getRank() {
        return rank;
    }

    // Setter method to set the rank of the card.
    public void setRank(String rank) {
        this.rank = rank;
    }

    // Getter method to retrieve the suit of the card.
    public String getSuit() {
        return suit;
    }

    // Setter method to set the suit of the card.
    public void setSuit(String suit) {
        this.suit = suit;
    }

    // Override the toString method to represent the card as a string.
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}