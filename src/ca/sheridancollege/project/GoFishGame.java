/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 * GoFishGame
 * @author Group2: Mann Mehta, Maharsh Patel, Shlok Zala, Vaishnavi Barot
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GoFishGame {
    private GroupOfCards groupOfCards;
    private List<Player> players;
    private int currentPlayerIndex;
    private Scanner scanner;

    public GoFishGame(List<String> playerNames) {
        groupOfCards = new GroupOfCards();
        players = new ArrayList<>();
        
        for (String playerName : playerNames) {
            players.add(new Player(playerName));
        }
        
        currentPlayerIndex = 0;
        scanner = new Scanner(System.in);
    }

    public void playGame() {
        boolean gameOver = false;
        
        while (!gameOver) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println("It's " + currentPlayer.getName() + "'s turn.");
            
            Player targetPlayer = askOtherPlayer(currentPlayer);
            
            if (targetPlayer != null) {
                System.out.print("Enter the rank you want to ask for: ");
                String rankToAskFor = scanner.nextLine();
                
                handlePlayerTurn(currentPlayer, targetPlayer, rankToAskFor);
            } else {
                System.out.println("Invalid player name. Exiting the game.");
                gameOver = true;
            }
            displayScores();  // Display scores after each turn

            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            
            if (isGameOver()) {
                gameOver = true;
                calculateAndDisplayScores();
            }
        }
    }

    private Player askOtherPlayer(Player currentPlayer) {
        System.out.print("Enter the name of the player you want to ask or 'exit' to end the game: ");
        String playerName = scanner.nextLine();
        
        if (playerName.equalsIgnoreCase("exit")) {
            return null;
        }
        
        Player targetPlayer = null;
        for (Player player : players) {
            if (player.getName().equalsIgnoreCase(playerName) && player != currentPlayer) {
                targetPlayer = player;
                break;
            }
        }
        
        return targetPlayer;
    }

    private void handlePlayerTurn(Player currentPlayer, Player targetPlayer, String rankToAskFor) {
        if (targetPlayer.hasCardOfRank(rankToAskFor)) {
            List<Card> matchingCards = targetPlayer.giveCardsOfRank(rankToAskFor);
            currentPlayer.addCardsToHand(matchingCards);
            System.out.println(currentPlayer.getName() + " received " + matchingCards.size() + " cards!");
        } else {
            System.out.println(targetPlayer.getName() + " doesn't have any " + rankToAskFor + " cards.");
            Card drawnCard = groupOfCards.drawCard();
            if (drawnCard != null) {
                currentPlayer.addCardToHand(drawnCard);
                System.out.println(currentPlayer.getName() + " drew a " + drawnCard);
            } else {
                System.out.println("The group of cards is empty.");
            }
        }
    }
    
private void displayScores() {
    System.out.println("Current Scores:");
    for (Player player : players) {
        System.out.println(player.getName() + ": " + player.getScore() + " sets");
    }
}

    private boolean isGameOver() {
        return groupOfCards.size() == 0 && players.stream().allMatch(Player::isHandEmpty);
    }

    private void calculateAndDisplayScores() {
        System.out.println("Final Scores:");
        for (Player player : players) {
            int score = player.calculateSets(); // Updated to use the calculateSets method
            System.out.println(player.getName() + ": " + score + " sets");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of players: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        List<String> playerNames = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter name for player " + (i + 1) + ": ");
            String playerName = scanner.nextLine();
            playerNames.add(playerName);
        }

        GoFishGame game = new GoFishGame(playerNames);
        game.playGame();
        
        scanner.close();
    }
}