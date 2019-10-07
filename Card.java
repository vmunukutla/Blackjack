
public class Card {
	private int number;
	private String name;
	public static final int minValue = 1;
	public static final int maxValue = 13;
	
	public Card(int number) {
		this.number = number;
		switch(number) {
			case 1:
				name = "Ace";
				break;
			case 2:
				name = "Two";
				break;
			case 3:
				name = "Three";
				break;
			case 4:
				name = "Four";
				break;
			case 5:
				name = "Five";
				break;
			case 6:
				name = "Six";
				break;
			case 7:
				name = "Seven";
				break;
			case 8:
				name = "Eight";
				break;
			case 9:
				name = "Nine";
				break;
			case 10:
				name = "Ten";
				break;
			case 11:
				name = "Jack";
				break;
			case 12:
				name = "Queen";
				break;
			case 13:
				name = "King";
				break;
			default:
		}
	}
	
	public int getNumber() {
		return number;
	}
	
	public int getValue() {
		return number >= 10 ? 10 : number;
	}
	
	public String getName() {
		return name;
	}
	
	public static int minValue() {
		return 1;
	}
	
	public static int maxValue() {
		return 13;
	}
}
