package nz.ac.auckland.softeng281.a2;

import java.util.ArrayList;
import java.util.List;

/**
 * you should change this class for TASK 1, 2, 3, 4.
 */
public class BlackJack {

	private List<Participant> players;
	private Participant dealer;
	private String name;
	private double highestGain;

	public BlackJack() {
		players = new ArrayList<>();
		dealer = new BotDealer("Dealer",players); // FIXME Task 2
		players.add(new HumanPlayer("Player1"));
		// ADDHERE Task 1
		players.add(new BotPlayer("Bot1"));
		players.add(new BotPlayer("Bot2"));
	}

	// getter setter for testing purposes
	public List<Participant> getPlayers() {
		return players;
	}

	public Participant getDealer() {
		return dealer;
	}

	public void setPlayers(List<Participant> players) {
		this.players = players;
	}

	public void setDealer(Participant dealer) {
		this.dealer = dealer;
	}

	public static void main(String[] args) {
		BlackJack game = new BlackJack();
		game.start();
	}

	protected void start() {
		Utils.printBlackJack();
		// create a new deck of cards
		Deck deck = new Deck();
		String result;
		do {
			for (Participant player : players) {
				player.play(deck);
			}
			// ADDHERE Task 2
			dealer.play(deck);
			checkWinner();
			System.out.println("Do you want to play again?");
			result = Utils.scanner.next();
			while (!result.equals("yes") && !result.equals("no")) {
				System.out.println("please type either \"yes\" or \"no\"");
				result = Utils.scanner.next();
			}
		} while (result.equals("yes"));
		printPlayerHighestGain();
	}

	public void checkWinner() {
		// TODO Task 3
		double totalGain;
		Hand dealerHand = dealer.getCurrentHand();
		for (Participant player : players) { 
			Hand playerHand = player.getCurrentHand();
			totalGain = player.getTotalGain();
			
			if (playerHand.isBlackJack()) {
				totalGain = totalGain + (1.5 * playerHand.getBet());
				player.setTotalGain(totalGain);
				System.out.println(player.getName() + " wins");
				
			} else if ((playerHand.getScore() > dealerHand.getScore()) && (playerHand.getScore() <= 21)) {
				
				totalGain = totalGain + playerHand.getBet();
				player.setTotalGain(totalGain);
				System.out.println(player.getName() + " wins");
				
			} else if ((dealerHand.getScore() > 21) && (playerHand.getScore() <= 21)) {
				
				totalGain = totalGain + playerHand.getBet();
				player.setTotalGain(totalGain);
				System.out.println(player.getName() + " wins");
				
			} else {
				totalGain = totalGain - playerHand.getBet();
				player.setTotalGain(totalGain);
			}
		}
	}

	public void printPlayerHighestGain() {
		// TODO Task 4
		highestGain = players.get(0).getTotalGain();
		name = players.get(0).getName();
		for (Participant player : players) { 
			if (player.getTotalGain() > highestGain) {
				highestGain = player.getTotalGain();
				name = player.getName();
			}	
		}
		System.out.println("The player with the highest gain is: " + name + " with " + highestGain + " chips"); 
	}
}
