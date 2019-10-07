import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Blackjack {

	private int numDecks;
	private Player one;
	private ShuffledDeck sd;
	private Scanner s;

	public Blackjack(int numDecks) {
		this.numDecks = numDecks;
		initGame();
		s = new Scanner(System.in);
	}

	public void initGame() {
		one = new Player();
		sd = new ShuffledDeck(numDecks);
	}

	public void play() {
		while(true) {
			initGame();
			System.out.println("----------------------");
			System.out.println("Game has started! It will go on until the deck runs out of cards or you quit the application.");
			one.takeCard(sd.draw());
			one.takeCard(sd.draw());
			ArrayList<Card> cards = one.getCards();
			System.out.println("You drew: " + cards.get(0).getName() + ".");
			System.out.println("You drew " + cards.get(1).getName() + ".");
			HashSet<Integer> workingSums = one.getWorkingSums();
			int finalSum = 0;
			if(!checkBlackjackOrBust()) break;
			else {
				while(!one.getBlackjack() && !one.getBust()) {
					printSum(workingSums);
					boolean hit = promptHitOrStay("Enter Hit or Stay.");
					if(hit) {
						Card c = sd.draw();
						System.out.println("You drew: " + c.getName() + ".");
						one.takeCard(c);
						workingSums = one.getWorkingSums();
					} else {
						if(workingSums.size() > 1) {
							System.out.println("Which score would you like to take?");
							finalSum = s.nextInt();
						} else {
							finalSum = (int) workingSums.toArray()[0];
						}
						if(promptResponse("Your final score is " + finalSum + ". Would you like to play again? Enter Yes or No.")) break;
						else {
							s.close();
							return;
						}
					}
				}
				if(!checkBlackjackOrBust()) break;
			}
		}
		s.close();
	}

	public void printSum(HashSet<Integer> workingSums) {
		ArrayList<Integer> sums = new ArrayList<Integer>();
		sums.addAll(workingSums);
		String result = "Your total sum is ";
		int sumSize = sums.size();
		if(sumSize == 1) {
			result += sums.get(0) + ".";
		}
		else if(sumSize == 2) {
			result += sums.get(0) + " or " + sums.get(1) + ".";
		}
		else {
			for(int i = 0; i < sums.size(); i++) {
				if(i == sums.size() - 1) {
					result += ", or " + sums.get(i) + ".";
				} else {
					result += ", " + sums.get(i);
				}
			}
		}
		System.out.println(result);
	}

	private boolean promptResponse(String str) {
		System.out.println(str);
		String response = s.nextLine().toLowerCase();
		while(!response.equals("yes") && !response.equals("no")) {
			System.out.println(str);
			response = s.nextLine().toLowerCase();
		}
		if(response.equals("no")) return false;
		return true;
	}

	private boolean promptHitOrStay(String str) {
		System.out.println(str);
		String response = s.nextLine().toLowerCase();
		response = response.toLowerCase();
		while(!response.equals("hit") && !response.equals("stay")) {
			System.out.println(str);
			response = s.nextLine().toLowerCase();
		}
		if(response.equals("stay")) return false;
		return true;
	}

	private boolean checkBlackjackOrBust() {
		if(one.getBlackjack()) {
			if(!promptResponse("Congrats! You got blackjack and you won. Would you like to play again? Enter Yes or No.")) return false;
		}
		else if(one.getBust()) {
			if(!promptResponse("Sorry! You bust and lost. Would you like to play again? Enter Yes or No.")) return false;
		}
		return true;
	}
}
