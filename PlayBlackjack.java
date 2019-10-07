import java.util.Scanner;

public class PlayBlackjack {
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Welcome to the game of blackjack.");
		System.out.println("How many decks would you like to play with? Press enter to play with"
				+ " the default number of decks: 1.");
		String response = s.nextLine();
		while(!response.isEmpty() && !response.matches("[0-9]+")) {
			System.out.println("How many decks would you like to play with? Press enter to play with"
					+ " the default number of decks: 1.");
			response = s.nextLine();
		}
		int numDecks = response.isEmpty() ? 1 : Integer.parseInt(response);
		Blackjack b = new Blackjack(numDecks);
		b.play();
		s.close();
	}
}
