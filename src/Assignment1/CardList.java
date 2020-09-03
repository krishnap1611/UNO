package Assignment1;

public class CardList {
	/* Attributes: first, keeps the front of the list
	 * count, keeps track of the number of Cards on the list
	 */
	private Card first;
	private int count;

	public int size() {
		return count;
	}
	
	public Card getFirst() {
		return this.first;
	}
	
	/* 
	 * Constructor (I did this one for you)
	 * if all is true, it creates a complete deck of 40 cards,
	 *                 4 colors, numbers from 0-9
	 * if all is false, it just creates an empty list
	 */
	public CardList(boolean all) {
		first=null;
		count=0;
		if (all) {
			for (Card.Colors color : Card.Colors.values()) {
				for (int number=0;number<=9;number++) {
					add(number,color);
				}	
			}
		}
	}

	/* DO THIS:
	 * traverse: is not actually used in the gui version of the program, 
	 * but it is useful while debugging the program
	 */
	public void traverse() {
		Card current=this.first;
		while (current!=null) {
			int j= current.getNumber();
			Card.Colors c= current.getColor();
			System.out.println(j + " " + c.toString());
			current=current.getNext();
		}
	}

	/* DO THIS:
	 * add: adds a card to the front of the list
	 * given number and color
	 */
	private void add(int number,Card.Colors color) {
		Card addfirst =new Card(number, color, first);
		Card current=this.first;
		addfirst.setNext(current);
		this.first = addfirst;
		count++;
	}

	/* DO THIS:
	 * add: adds a card to the front of the list
	 * given a reference to the new card
	 */
	private void add(Card card) {
		Card current=this.first;
		card.setNext(current);
		this.first = card;
		count++;
	}
	
	/* DO THIS:
	 * countCards: Traverse the list and return the number of cards.
	 *     When complex operations are done on a list, such as
	 *     concatenation of lists, countCards is used to make sure that the
	 *     number of cards is kept updated (just there because we are lazy
	 *     and do not want to think about how to compute the new number
	 *     of cards based on the original ones).
	 */
	private int countCards() {
		Card current=this.first;
		int c=0;
		while (current!=null) {
			current=current.getNext();
			c++;
		}
		return c; 
	}

	/* DO THIS:
	 * Append a new list of cards "list" at the end of the current list (this)
	 * Notice that it might be possible for this.first to be null
	 */
	public void concatenateWith(CardList list) {
		Card current=this.first;
		if (this.first==null) {
			this.first=list.first;
		}
		else {
			while (current!=null) {
				if(current.getNext()==null)
					break;
				else
					current=current.getNext();
			}
		current.setNext(list.first);
		}
	}

	/* START FROM HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * moveTo: move the front card from this to the front of destination
	 */
	public void moveTo(CardList destination) {
		if (this.first!=null) {
			Card current=this.first;
			this.first=current.getNext();
			current.setNext(destination.first);
			destination.first=current;
		}
			
	}
	
	
	/* DO THIS:
	 * moveTo: move the first num cards from this to the front of destination,
	 * it can use the (CardList destination) method repeatedly
	 */	
	public void moveTo(int num,CardList destination) {
		for(int i=0; i<num; i++)
			moveTo(destination);
		
	}

	/* DO THIS:
	 * moveTo: Given a Card x, it finds the card on this list and
	 *         moves it to the front of the destination list.
	 * 
	 */
	public boolean moveTo(Card x,CardList destination) {
		boolean found = false;
		Card current=this.first;
		Card prev=null;
		if (current.getNumber() ==x.getNumber() && current.getColor()==x.getColor()) {
			found=true;
		}

		else {
			while ((current.getNumber()!=x.getNumber()) || (current.getColor()!=x.getColor())) {				
				prev=current;
				if(current.getNext()!=null) {
					current=current.getNext();
					prev.setNext(current.getNext());
				}
			}
			found=true;
			
		}
		if(found) {
			destination.add(current);
		}
			return found;
	}
		
	/* DO THIS:
	 * shuffle: Easy way is to create two new empty lists,
	 *          repeat split number of times: move the
	 *          first card of this to the first list, and then 
	 *          the next one to the second list,
	 *          finally, concatenate the two lists to this. 
	 */
	public void shuffle(int split) {
		CardList a=new CardList(false);
		CardList b=new CardList(false);
		Card current=this.first;
		for (int i=0; i<split; i++) {
			if(current.getNext()!=null) {
				this.first=current.getNext();
				a.add(current);
				current=this.first;
			}
			else
				break;
			if(current.getNext()!=null) {
				this.first=current.getNext();
				b.add(current);
				current=this.first;
			}
			else
				break;
		}
		this.concatenateWith(a);
		this.concatenateWith(b);
	}
	
	/* DO THIS:
	 * search: return a card that matches either the number or color
	 *         of the given card x.
	 *         You must use the matches(false,x) method that you wrote for the
	 *         Card class.
	 */
	public Card search(Card x) {
		Card current=this.first;
		while (current!=null) {
			if (current.matches(false, x)) {
				return current;
			}
			current=current.getNext();
		}
		return null;
	}

	
	/* DO THIS:
	 * getCard: returns a Card in this list that matches exactly
	 *          (use matches(true,card) method in Card) the given card
	 */
	public Card getCard(Card card) {

		Card current=this.first;
		while (current!=null) {
			if (current.matches(true, card)) {
				return current;
			}
			current=current.getNext();
		}
		return null;
	}

}
