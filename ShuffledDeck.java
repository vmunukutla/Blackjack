import java.util.*;

public class ShuffledDeck {
	private LinkedList<Card> cards;
	private static final int numOfEachCard = 4;
	private Random r;
	
	public ShuffledDeck(int numDecks) {
		cards = initCards(numDecks);
	}
	
	public LinkedList<Card> initCards(int numDecks) {
		int numberOfCards = Card.maxValue() * numOfEachCard * numDecks;
		Card[] cardSetUp = new Card[numberOfCards];
		int cardIndex = 0;
		for(int i = Card.minValue(); i <= Card.maxValue(); i++) {
			for(int j = 0; j < numOfEachCard; j++) {
				for(int k = 0; k < numDecks; k++) {
					cardSetUp[cardIndex++] = new Card(i);
				}
			}
		}
		r = new Random();
		for(int i = 0; i < numberOfCards; i++) {
			int index = r.nextInt(numberOfCards - i) + i;
			Card one = cardSetUp[i];
			cardSetUp[i] = cardSetUp[index];
			cardSetUp[index] = one;
		}
		return new LinkedList<Card>(Arrays.asList(cardSetUp));
	}
	
	public List<Card> getCards(){
		return cards;
	}
	
	public Card draw() {
		if(!cards.isEmpty()) {
			return cards.remove();
		}
		return null;
	}
	
}
