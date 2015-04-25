/** Program6 - creates different linked lists and allows the user to
 manipulate the lists using different actions. */

import java.util.Scanner;

public class Program6{
	
	static Scanner keyboard;
	
	public static void main(String[] args){
		
		int listChoice, answer = 0;
		double totalValue = 0;
		List inventOne = new List();
		List inventTwo = new List();
		List stringList = new List();
		List temp = null;
		Object item;
		keyboard = new Scanner(System.in);
		
		System.out.println("This program will allow you to manupulate a list. " +
				"\nYou will get to choose from three different " +
				"\nlists to manipulate.");
		
		do{	
			listChoice = listSelection();
			if(listChoice == 1){
				temp = inventOne;
			}
			else if(listChoice == 2){
				temp = inventTwo;
			}
			else if(listChoice == 3){
				temp = stringList;
			}
			if(listChoice != 4){
				answer = getAction(listChoice);
				switch(answer){
					case 1:
						List newList = new List(); 
						if (listChoice == 1){
							inventOne = newList;
						}
						else if(listChoice == 2){
							inventTwo = newList;
						}
						else{
							stringList = newList;
						}
						temp = newList;
						System.out.println("List " + listChoice + " " +
								"has been emptied.");
						break;
					case 2:
						temp = insertItem(temp, listChoice);
						break;
					case 3:
						temp = insertAtEnd(temp, listChoice);
						break;
					case 4:
						temp = deleteRange(temp);
						break;
					case 5:
						temp = deleteItem(temp, listChoice);
						break;
					case 6:
						item = retreive(temp, listChoice);
						System.out.println(item);
						break;
					case 7:
						List listOf = find(temp, listChoice); 
						System.out.println("\nlist of matching positions: \n" 
								+ listOf);
						break;
					case 8:
						System.out.println("Count: " + temp.getCount());
						break;
					case 9:
						System.out.println(temp);
						break;
					case 10:
						if(listChoice == 3){
							System.out.println("\nCannot perform the " +
							"total value calculation\nwith this list.");
						}
						else{
							totalValue = calcTotalValue(temp);
							System.out.printf("The total value of all " +
							"items within\nthis list is: %.2f", totalValue);
						}
						break;
					case 11:
						break;
					default:
						System.out.println("\nPlease enter a " +
								"correct option choice");
						break;
				}
			}
		}while(listChoice != 4 && answer != 11);
		System.out.println("Goodbye");
	}
	/* getAction - collects the action 
	 choice */
	public static int getAction(int listChoice){
		
		int answer = 0;
		if(listChoice < 3){
			do{
				System.out.println("\nWhat operation would you " +
						"like to perform?:\n\t1) Set List to a new" +
						" List\n\t2) Insert a given item at a given " +
						"position\n\t3) Insert item at the end\n\t4) " +
						"Delete a range of items\n\t5) Delete an item" +
						"\n\t6) Return an item at a given position\n\t7) " +
						"Return a list of positions where an item is " +
						"found\n\t8) Return the number of items from " +
						"the list\n\t9) Print the list\n\t10) Compute " +
						"total value for all inventory items in list" +
						"\n\t11) Quit");
				answer = keyboard.nextInt();
				if(answer > 11 || answer < 0){
					System.out.println("\nPlease enter a " +
							"correct answer option.");
				}
			}while(answer > 11 || answer < 0);
		}
		else{
			do{
				System.out.println("\nWhat operation would " +
						"you like to perform?:\n\t1) Set " +
						"List to a new List\n\t2) Insert a given " +
						"item at a given position\n\t3) Insert item " +
						"at the end\n\t4) Delete a range of items" +
						"\n\t5) Delete an item\n\t6) Return an " +
						"item at a given position\n\t7) Return a " +
						"list of positions where an item is " +
						"found\n\t8) Return the number of" +
						" items from the list\n\t9) Print the list" +
						"\n\t10) Quit");
				answer = keyboard.nextInt();
				if(answer > 10 || answer < 0){
					System.out.println("\nPlease enter a" +
							" correct answer option.");
				}
			}while(answer > 10 || answer < 0);
			if(answer == 10){
				answer++;
			}
		}
		return answer;
	}
	/* listSelection - collects the list
	 choice */
	public static int listSelection(){
		
		int listChoice = 0;
		do{
			System.out.println("\nWhat list would you like to manipulate?" +
			"\n\t1) Inventory list one" +
			"\n\t2) Inventory list two" +
			"\n\t3) String list" +
			"\n\t4) Quit");
			listChoice = keyboard.nextInt();
			if(listChoice > 4 || listChoice < 1){
				System.out.println("\nPlease enter a correct " +
						"answer option.");
			}
		}while(listChoice > 4 || listChoice < 1);
		return listChoice;
	}
	/*calcTotalPrice - calculates the total price
	 of all items in the list*/
	public static double calcTotalValue(List temp){
		
		double totalValue = 0;
		Inventory item = null;
		for(int i = 1; i <= temp.getCount(); i++){
			item = (Inventory) temp.retrieve(i);
			totalValue += (item.getCostPer() * item.getQuantity());
		}
		return totalValue;
	}
	/* find - finds an object within a list*/
	public static List find(List temp, int listChoice){
		
		String text;
		List returnList = null;
		Inventory newInvent = null;
		if(listChoice < 3){
			System.out.print("\nWhat is the name of the " +
					"inventory item you want to find?: ");
			text = keyboard.next();
			newInvent = new Inventory(text, 0, 0);
			returnList = temp.find(newInvent);
		}
		else{
			System.out.print("\nWhat is the text you would like to find?: ");
			text = keyboard.next();
			returnList = temp.find(text);
		}
		return returnList;
	}
	/* retrieve - retrieves an item from a list */
	public static Object retreive(List temp, int listChoice){
		
		int position = 0;
		Object item;
		
		System.out.print("What is the position of the object you" +
				"\nwould like to retrieve?: ");
		position = keyboard.nextInt();
		item = temp.retrieve(position);
		
		return item;
	}
	/* deleteItem - deletes an item from a list */
	public static List deleteItem(List temp, int listChoice){
		
		String text;
		Inventory newInvent = null;
		if(listChoice < 3){
			System.out.print("\nWhat is the name of the " +
					"inventory item you want to delete?: ");
			text = keyboard.next();
			newInvent = new Inventory(text, 0, 0);
			temp.deleteItem(newInvent);
		}
		else{
			System.out.print("\nWhat is the name of the text" +
					"\nyou would like to delete?: ");
			text = keyboard.next();
			temp.deleteItem(text);
		}
		System.out.println("\nYour item has been deleted.");
		return temp;
	}
	/* deleteRange - deleted a range of
	 items from a list */
	public static List deleteRange(List temp){
		
		int start, end;
		System.out.print("\nWhat is the start of the range " +
				"you would like to delete?: ");
		start = keyboard.nextInt();
		System.out.print("\nWhat is the end of the range " +
				"you would like to delete?: ");
		end = keyboard.nextInt();
		temp.deleteRange(start, end);
		System.out.println("\nYour range has been deleted.");
		return temp;
	}
	/* insertItem - inserts an object at a given 
	 position */
	public static List insertItem(List temp, int listChoice){
		
		int position = 0;
		String text;
		
		System.out.print("\nWhat is the position?: ");
		position = keyboard.nextInt();
		if(listChoice < 3){
			Inventory newInv = makeInv();
			temp.insert(position, newInv);
		}
		else{
			System.out.print("\nWhat is the text you " +
					"would like to insert?: ");
			text = keyboard.next();
			temp.insert(position, text);
		}
		System.out.println("\nYour item has been inserted.");
		return temp;
	}
	/* insertAtEnd - inserts an item at the end of the list*/
	public static List insertAtEnd(List temp, int listChoice){
		
		String text;
		
		if(listChoice < 3){
			Inventory newInv = makeInv();
			temp.insertAtEnd(newInv);
		}
		else{
			System.out.print("What is the text you would like to insert?: ");
			text = keyboard.next();
			temp.insertAtEnd(text);
		}
		System.out.println("\nYour item has been inserted.");
		return temp;
	}
	/* makeInv - makes an inventory 
	 object */
	private static Inventory makeInv(){
		
		int quantity = 0;
		String name;
		double costPer = 0;
		
		System.out.print("\nWhat is the name?: ");
		name = keyboard.next();
		System.out.print("\nWhat is the quantity?: ");
		quantity = keyboard.nextInt();
		System.out.print("\nWhat is the cost per item?: ");
		costPer = keyboard.nextInt();
		Inventory newInv = new Inventory(name, quantity, costPer);
		
		return newInv;	
	}
}
class Inventory{
	
	private String _name;
	private int _quantity;
	private double _costPer;
	
	/* Inventory - is the Inventory constructor */
	public Inventory(String name, int quantity, double costPer){
		
		_name = name;
		_quantity = quantity;
		_costPer = costPer;
	}
	/* getName - returns the name 
	data member */
	public String getName(){
		
		return _name;
	}
	/* getQuantity - returns the quantity
	 data member */
	public int getQuantity(){
		
		return _quantity;
	}
	/* getCostPer - returns
	 the costPer data member*/
	public double getCostPer(){
		
		return _costPer;
	}
	/* toString - returns a string 
	of the Inventory item */
	public String toString(){
		
		return "Name: " + _name +
				"\nQuantity " + _quantity +
				"\nCost Per Item $" + _costPer + "\n";
	}
	/* equals - returns true or false
	depending on if the given item is equal */
	public boolean equals(Object other){
		
		if(other instanceof Inventory){
			Inventory newInvent = (Inventory) other;
			return newInvent.getName().equals(_name);
		}
		else{
			return false;
		}
	}
}
class Link{
	
	public Object _data;
	public Link _next;
	
}
class List{
	
	private Link _head;
	private Link _tail;
	private int _count;
	
	/* List - constructor for the List
	 class. */
	public List(){
		
		_head = null;
		_count = 0;
		_tail = null;
	}
	/* getCount - returns the count */
	public int getCount(){
		
		return _count;
	}
	/* deleteItem -  deletes the item that is compatible
	 with the given item */
	public void deleteItem(Object item){
		
		if(_head == null) return;
		Link current = _head;
		Link previous = null;
		while(current != null){
			if(current._data.equals(item)){
                if (_head == current){
                	_head = _head._next;
                }
                else{
	                if(current._next == null){
	                	_tail = previous;
	                }
                	previous._next = current._next;
                }
                current = current._next;
                _count--;
			}
			else{
				previous = current;
				current = current._next;
			}
		}
	}
	/* toString - returns a list of the items
	 within the list */
	public String toString(){
		
		String s = "";
		for(Link current = _head; current != null; 
				current = current._next){
			s = s + "\n" + current._data;
		}
		return s;
	}
	/* find - Creates a list of positions that
	 are equal to the given item */
	public List find(Object item){
		
		List newList = new List();
		Link current = _head;
		int i = 1;
		while(current != null){
			if(current._data.equals(item)){
				newList.insertAtEnd(i);
			}
			current = current._next;
			i++;
		}
		return newList;
	}
	/* retrieve - retrieves an object located at the 
	 given position */
	public Object retrieve(int position){
		
		if(position < 1 || position > _count) return null;
		Object returnObj = null;
		Link current = _head;
		int i = 1;
		if (position <= _count){
			while(current != null && i < position){
				current = current._next;
				i++;
			}
		}
		returnObj = current._data;
		return returnObj;
	}
	/* deleteRange - deletes the items that are
	contained within the given start and end positions */
	public void deleteRange(int start, int end){
		
		if(start < 1 || start > _count ||
		end < 1 || end > _count || _head == null) return;
		Link current = _head;
		Link previous = null, startLink = null, endLink = null;
		int i = 0;
		while (current != null && i < end){
            if (i == (start - 1) && previous != null){
                startLink = previous;
            }
            if (i == (end - 1)){
                endLink = current._next;
                if (endLink == null){
                    _tail = startLink;
                }
            }
            previous = current;
            current = current._next;
            i++;
        }
        if (start == 1){
            _head = endLink;
        } 
        else{
            startLink._next = endLink;
        }
		_count -= end - start + 1;
	}
	/*insertAtEnd - inserts an object at the end 
	 of the list */
	public void insertAtEnd(Object item){
		
		Link add = new Link();
		add._data = item;
		if(_count == 0){
			add._next = _head;
			_head = add;
			_tail = _head;
		}
		else{
			add._next = null;
			_tail._next = add;
			_tail = add;
		}
		_count++;
	}
	/* insert - inserts an Object at the given position */
	public void insert(int position, Object item){
		
		if((position > (_count + 1)) || 
				position < 1) return;
		Link current = _head;
		Link previous = null;
		Link add = new Link();
		add._data = item;
		if(position == 1){
			add._next = _head;
			_head = add;
			_tail = _head;
		}
		else if(position == (_count + 1)){
			add._next = null;
			_tail._next = add;
			_tail = add;
		}
		else{
			for(int i = 0; i < (position - 1); i++){
				previous = current;
				current = current._next;
			}
			if(current == null) return;
			if(previous != null){
				previous._next = add;
				add._next = current;
			}
		}
		_count++;
	}
}