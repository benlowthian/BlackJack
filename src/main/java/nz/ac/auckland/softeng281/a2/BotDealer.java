package nz.ac.auckland.softeng281.a2;

import java.util.List;
import java.util.Random;

/**
 * you should change this class for TASK 2
 */
public class BotDealer extends Participant {

	private List<Participant> players;

	public BotDealer(String name, List<Participant> players) {
		super(name);
		// ADDHERE
		this.players = players;
	}

	@Override
	public Action decideAction() {
		// TODO
		int losingTo = 0;
		Hand dealerHand = this.getCurrentHand();
		int dealerScore = dealerHand.getScore();
		
		for (Participant player : players) {
			Hand playerHand = player.getCurrentHand();
			if (((playerHand.getScore() > dealerScore) && (playerHand.getScore() < 22)) || (dealerScore > 21)) {
				losingTo++;
			}
		}
		
		if (losingTo >= 2) {
			return Action.HIT;
		}
		
		return Action.HOLD;
	// FIXME
	}

	@Override
	/**
	 * do not touch this method
	 */
	public int makeABet() {
		// the Dealer doesn't bet so is always zero
		return 0;
	}
}
