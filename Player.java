import java.util.ArrayList;
import java.util.HashSet;

public class Player {
	private ArrayList<Card> cards;
	private ArrayList<Integer> possibleSums;
	private boolean bust;
	private boolean blackjack;
	
	public Player() {
		cards = new ArrayList<Card>();
		possibleSums = new ArrayList<Integer>();
		bust = false;
		blackjack = false;
	}
	
	public void takeCard(Card c) {
		cards.add(c);
		if(c.getValue() == 1) {
			if(possibleSums.isEmpty()) {
				possibleSums.add(1);
				possibleSums.add(11);
			} else {
				int size = possibleSums.size();
				for(int i = 0; i < size; i++) {
					possibleSums.set(i, possibleSums.get(i) + 1);
					possibleSums.add(possibleSums.get(i) + 11);
				}
			}
		} else {
			if(possibleSums.isEmpty()) possibleSums.add(c.getValue());
			else {
				for(int i = 0; i < possibleSums.size(); i++) {
					possibleSums.set(i, possibleSums.get(i) + c.getValue());
				}
			}
		}
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public HashSet<Integer> getWorkingSums() {
		HashSet<Integer> workingSums = new HashSet<Integer>();
		for(int sum : possibleSums) {
			if(sum == 21) blackjack = true;
			if(sum < 21) workingSums.add(sum);
		}
		if(workingSums.isEmpty()) bust = true;
		return workingSums;
	}
	
	public boolean getBlackjack() {
		return blackjack;
	}
	
	public boolean getBust() {
		return bust;
	}
}
