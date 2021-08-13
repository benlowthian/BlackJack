package nz.ac.auckland.softeng281.a2;

import java.util.Random;

/**
 * you should change this class for TASK 1
 */
public class BotPlayer extends Participant {

	public BotPlayer(String name) {
		super(name);
	}

	
	
	
	@Override
	public Action decideAction() {
		// TODO
		Hand currentHand = this.getCurrentHand();
		int score = currentHand.getScore();
		if (score >= 17) {
			return Action.HOLD;
		} else {
			return Action.HIT;
		}
		 // FIXME
	}

	@Override
	public int makeABet() {
		// TODO
		Random random = new Random();
		int low = 1;
		int high = 100;
		int result = random.nextInt(high-low) + low;
	 // FIXME
		return result;
	}
}
