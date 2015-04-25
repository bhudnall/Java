/** 
 Brian Hudnall - BinaryTree. This program is a binary search tree. 
 The user will be able to create a new tree that consists of MLB player profiles 
 (team name, player number, player name, and batting average) and 
 have the option to add to the tree, delete from the tree, search, 
 get the height, size, average level, and either tree or normal 
 display of your table. They can also sort a file and create several 
 trees of randomly generated numbers and compute the worst case 
 retrieval time and expected case (average depth) for each size.
*/

import java.io.*;
import java.util.*;

public class BinaryTree{
	
	static Scanner keyboard;
	
	public static void main(String[] args){
		
		int answer, action;
		Table temp = new Table();
		Table tempFile = null;
		Table newTable = null;
		MLBPlayer player = null;
		keyboard = new Scanner(System.in);
		
		System.out.println("This program is a binary search tree. You will" +
				"\nbe able to create a new tree that consists of MLB player profiles " +
				"\n(team name, player number, player name, and batting average)" +
				"\nand have the option to add to the tree, delete from" +
				"\nthe tree, search, get the height, size, average level," +
				"\nand either tree or normal display of your table." +
				"\nYou can also sort a file and create several trees of" +
				"\nrandomly generated numbers and compute the worst case" +
				"\nretrieval time and expected case (average depth) for " +
				"each size.");
		
		do{
			answer = getAnswer();
			if(answer == 1){
				do{
					action = testTableOptions();
					temp = performTableActions(temp, action);
				}while(action != 10);
			}
			else if (answer == 2){
				tempFile = readFile();
				System.out.println(tempFile);
			}
			else if (answer == 3){
				fillTable();
			}
		}while(answer != 4);
		System.out.println("\nGoodbye.");
	}
	/**
	Creates several tables filled with randomly 
	generated numbers. Each size has 10 trees generated.
	Computes and displays the worst case retrieval time and 
	expected case(average depth) for each size.
	*/
	public static void fillTable(){
		
		int nextInt = 0, size = 0; 
		double maxHeight = 0;
		double averageLevel = 0, height = 0;
		Table theTable = null;
		System.out.print("\nExpected\tWorst case" +
        		"\tSize\ncase\t\tretrieval"); 
        for(int i = 4; i < 16; i++){ 
            for(int j = 0; j < 10; j++){
	            theTable = new Table();
	            do {
	            	size = theTable.getSize();
	                Random generator = new Random();
	                nextInt = generator.nextInt();
	                KeyComparableNumber newNum = new KeyComparableNumber(nextInt);
	                theTable.insert(newNum);
	            }while (size < Math.pow(2, i));
	            averageLevel += theTable.getAverageLevel();
	            height = theTable.getHeight();
	            if(height > maxHeight){
	                maxHeight = height;  
	            }
            } 
            averageLevel = averageLevel / 10;
            System.out.printf("\n%.2f\t\t%.2f\t\t%.2f",
            		averageLevel, maxHeight, Math.pow(2, i));
        }
        System.out.println('\n');
	}
	/**
	Collects a file name and then creates
	a binary search tree using the contents
	of the file, the tree is then printed
	in order. 
	*/
	public static Table readFile(){
		
		Table newTable = new Table();
		String teamName, playerName;
		double battingAve;
		int playerNum;
		MLBPlayer player = null;
		
		System.out.print("\nWhat is the file name?: ");
		String fileName = keyboard.next();
		try{
			File file = new File(fileName);
			Scanner inputStream = new Scanner(file);
			do{
				playerNum = inputStream.nextInt();
				teamName = inputStream.next();
				playerName = inputStream.next();
				battingAve = inputStream.nextDouble();
				player = new MLBPlayer(playerName, battingAve,
									   playerNum, teamName);
				newTable.insert(player);
			}while(inputStream.hasNext());
		}
		catch (IOException ioe) {
			System.out.println("\nFile access failure.");
		}
		return newTable;
	}
	//Searches for an MLBPlayer from the given table
	public static MLBPlayer tableSearch(Table theTable){
		
		String teamName;
		int jerseyNum;
		MLBPlayerKey playerKey = null;
		MLBPlayer player = null;
		
		System.out.print("\nWhat is the team name?: ");
		teamName = keyboard.next();
		System.out.print("\nWhat is the jersey number?: ");
		jerseyNum = keyboard.nextInt();
		
		playerKey = new MLBPlayerKey(jerseyNum, teamName); 
		player = (MLBPlayer) theTable.search(playerKey);
		return player;

	}
	//Creates a table
	public static Table createTable(){
		
		Table newTable = new Table();
		return newTable;
	}
	//Deletes an MLBPlayer item from the given table
	public static Table tableDelete(Table theTable){
		
		String teamName;
		int jerseyNum;
		MLBPlayerKey newPlayer = null;
		
		System.out.print("\nWhat is the team name?: ");
		teamName = keyboard.next();
		System.out.print("\nWhat is the jersey number?: ");
		jerseyNum = keyboard.nextInt();
		newPlayer = new MLBPlayerKey(jerseyNum, teamName);
		
		theTable.delete(newPlayer);
		return theTable;
	}
	//Inserts an MLBPlayer item into the given table
	public static Table tableInsert(Table theTable){
		
		String playerName, teamName;
		double battingAve;
		int jerseyNum;
		MLBPlayer newPlayer = null;
		
		System.out.print("\nWhat is the players name (no spaces)?: ");
		playerName = keyboard.next();
		System.out.print("\nWhat is the batting average?: ");
		battingAve = keyboard.nextDouble();
		System.out.print("\nWhat is the jersey number?: ");
		jerseyNum = keyboard.nextInt();
		System.out.print("\nWhat is the team name?: ");
		teamName = keyboard.next();
		newPlayer = new MLBPlayer(playerName, battingAve,
							  jerseyNum, teamName);
		
		theTable.insert(newPlayer);
		return theTable;
	}
	/** Shows the initial menu options
	 to the user and collects the answer
	 option.
	 */
	public static int getAnswer(){
		
		int answer;
		do{
			System.out.print("\nPlease choose an option: " +
					"\n\t1) Test an MLB Player table" +
					"\n\t2) Sort a file" +
					"\n\t3) Test many tables" +
					"\n\t4) Quit");
			answer = keyboard.nextInt();
			if(answer < 1 || answer > 4){
				System.out.println("\nPlease enter a correct options.");
			}
		}while(answer < 1 || answer > 4);
		return answer;
	}
	/**
	Shows action menu options and collects 
	actions from the user to perform
	on a table.
	 */
	public static int testTableOptions(){
		
		int action = 0;
		do{
			System.out.println("\nPlease choose an action: " +
					"\n\t1) Initialize table" +
					"\n\t2) Insert an item" +
					"\n\t3) Delete an item" +
					"\n\t4) Search for an item" +
					"\n\t5) Get height of tree" +
					"\n\t6) Get the size of the tree - number of nodes" +
					"\n\t7) Return the average level of nodes in the tree" +
					"\n\t8) Show the tree in a tree like fashion" +
					"\n\t9) Display the entire contents of the table in order" +
					"\n\t10) Quit");
			action = keyboard.nextInt();
			if(action > 10 || action < 1){
				System.out.println("\nPlease enter a correct option.");
			}
		}while(action > 10 || action < 1);
		return action;
	}
	/**
	Performs the actions on the given table
	such as insert, delete, print player, height,
	print as a tree, and print in order.
	 */
	public static Table performTableActions(Table temp, int action){
		
		Table newTable = new Table();
		MLBPlayer player = null;
		
		switch(action){
			case 1:
				//Initialize
				newTable = createTable();
				temp = newTable;
				System.out.println("\nYour table has been created.");
			break;
			case 2:
				//Insert
				temp = tableInsert(temp);
				System.out.println("\nYour item has been inserted.");
			break;
			case 3:
				//Delete
				temp = tableDelete(temp);
				System.out.println("\nYour item has been deleted.");
			break;
			case 4:
				//Search
				player = tableSearch(temp);
				System.out.println("\nPlayer name: " + player.getPlayerName() +
						"\nTeam name: " + player.getTeamName() +
					    "\nPlayer number: " + player.getJerseyNumber() +
						"\nBatting average: " + player.getBattingAve());
			break;
			case 5:
				//Height
				System.out.println("\nThe height is: " + temp.getHeight());
			break;
			case 6:
				//Size - number of nodes
				System.out.println("\nThe size is: " + temp.getSize());
			break;
			case 7:
				//Average level
				System.out.println("\nThe table average is: " +
						temp.getAverageLevel());
			break;
			case 8:
				//Show tree
				System.out.println(temp.showTree());
			break;
			case 9:
				//toString
				System.out.println(temp.toString());
			break;
		}
		return temp;
	}
}
/**
This is the interface used for many of the 
different classes. It forces a compareTo
and toString method
*/
interface KeyComparable{
	
	public int KeyCompareTo(KeyComparable other);
	public String toStringKey();
}
/**
TreeNote class is the individual nodes within
the tree 
 */
class TreeNode{
	
	public KeyComparable data;
	public TreeNode right;
	public TreeNode left;
	
	
	//Node constructor 
	public TreeNode(KeyComparable item){
		
		data = item;
		left = right = null;
	}
}
/**
MLBPlayerKey is a class for the MLBPlayer
and the parent class for the MLBPlayer
*/
class MLBPlayerKey implements KeyComparable{
	
	private int _jerseyNumber;
	private String _teamName;
	
	//Constructor
	public MLBPlayerKey(int jerseyNumber, String teamName){
		
		_jerseyNumber = jerseyNumber;
		_teamName = teamName;
	}
	//Jersey number accessor
	public int getJerseyNumber(){
		return _jerseyNumber;
	}
	//Team Name accessor
	public String getTeamName(){
		return _teamName;
	}
	/**
	CompareTo method, returns -1 if less than other,
	0 if equal, and 1 if greater. Order items by
	team name and then by jersey number.
	*/
	public int KeyCompareTo(KeyComparable other){
		
		int answer = 0;
		if(other instanceof MLBPlayerKey){
			MLBPlayerKey otherPlayer = (MLBPlayerKey) other;
			if(otherPlayer._jerseyNumber == _jerseyNumber &&
			   (otherPlayer._teamName.compareTo(_teamName) == 0)){
				answer = 0;
			}
			else if(_teamName.compareTo(otherPlayer._teamName) < 0){
				answer = -1;
			}
			else if(_teamName.compareTo(otherPlayer._teamName) > 0){
				answer = 1;
			}
			else{
				if(_jerseyNumber > otherPlayer._jerseyNumber){
					answer = 1;
				}
				else{
					answer = -1;
				}
			}
		}
		return answer;
	}
	/**
	toString key method,  produces an abbreviated
	summary of the item. It is only used by the 
	'showTree' method, and nothing else.
	 */
	public String toStringKey(){
		
		String s = "\n";
		String abbrev;
		abbrev = _teamName.substring(0, 3);
		s = _jerseyNumber + abbrev + "\n";
		return s;
	}
	//toString method, should output # of team
	public String toString(){
		
		return "\n#" + _jerseyNumber + " for the " + _teamName;
	}
}
/**
 The subclass of the MLBPlayerKey class.
 contains the MLB player's name and 
 batting average.  
 */
class MLBPlayer extends MLBPlayerKey{
	
	private String _playerName;
	private double _battingAve;
	
	//Constructor
	public MLBPlayer(String playerName, double battingAve,
					 int jerseyNumber, String teamName){
		super(jerseyNumber, teamName);
		_playerName = playerName;
		_battingAve = battingAve;
	}
	//Player name accessor
	public String getPlayerName(){
		return _playerName;
	}
	//PLayer batting average
	public double getBattingAve(){
		return _battingAve;
	}
	public String toString(){
		return super.toString() +
			"\nPlayer Name: " + _playerName +
			"\nBatting average: " + _battingAve +
			"\n";
	}
}
/**
Class that contains a single data member
that is a number, which is a key.
*/
class KeyComparableNumber implements KeyComparable{
	
	private int _key;
	
	//Constructor
	public KeyComparableNumber(int key){
		_key = key;
	}
	//Accessor
	public int getKey(){
		return _key;
	}
	//compareTo method to comply with interface
	public int KeyCompareTo(KeyComparable other){
		
		int returnNum = 0;
		if (other instanceof KeyComparableNumber){
			KeyComparableNumber otherNumber = (KeyComparableNumber) other;
			if(_key < otherNumber._key){
				returnNum = -1;
			}
			else if(_key > otherNumber._key){
				returnNum = 1;
			}
			else{
				returnNum = 0;
			}
		}
		return returnNum;
	}
	//toStringKey method to comply with interface
	public String toStringKey(){
		return toString();
	}
	public String toString(){
		String keyString = "" + _key;
		return keyString;
	}
}
/**
Table class, creates a table of items. Used by the main
application. Other data members can be inserted and
manipulated along with printed by this class.
*/
class Table{
	
	private TreeNode _root;
	
	/**
	 Insert method number 1. Inserts root 
	 if empty passes if not empty
	 */
	public void insert(KeyComparable item){
		_root = insert(_root, item);
	}
	//Inserts item in appropriate location
	private TreeNode insert(TreeNode myRoot, KeyComparable item){
		
		if(myRoot == null){
			myRoot = new TreeNode(item);
		}
		else{
			int comp = item.KeyCompareTo(myRoot.data);
			if(comp < 0){
				myRoot.left = insert(myRoot.left, item);
			}
			else if(comp > 0){
				myRoot.right = insert(myRoot.right, item);
			}	
		}
		return myRoot;
	}
	// Search method number 1. 
	public KeyComparable search(KeyComparable key){
		return search(_root, key);
	}
	// Search method number 2.
	private KeyComparable search(TreeNode myRoot, KeyComparable key){
		
		if(myRoot == null){
			return null;
		}
		else{
			int comp = key.KeyCompareTo(myRoot.data);
			if(comp == 0){
				return myRoot.data;
			}
			else if(comp < 0){
				return search(myRoot.left, key);
			}
			else{
				return search(myRoot.right, key);
			}
		}
	}
	// Delete method number 1.
	public void delete(KeyComparable key){
		_root = delete(_root, key);
	}
	// Delete method number 2.
	private TreeNode delete(TreeNode myRoot, KeyComparable key){
		
		if(myRoot != null){
			int comp = key.KeyCompareTo(myRoot.data);
			if(comp < 0){
				myRoot.left = delete(myRoot.left, key);
			}
			else if(comp > 0){
				myRoot.right = delete(myRoot.right, key);
			}
			else{
				if(myRoot.left == null && myRoot.right == null){
					myRoot = null;
				}
				else if(myRoot.left == null && myRoot.right != null){
					myRoot = myRoot.right;
				}
				else if(myRoot.right == null && myRoot.left != null){
					myRoot = myRoot.left;
				}
				else{
					KeyComparable rep = findMax(myRoot.left);
					myRoot.data = rep;
					myRoot.left = delete(myRoot.left, rep);
				}
			}
		}
		return myRoot;
	}
	// findMax number 1
	public KeyComparable findMax(){
		if(_root != null){
			return findMax(_root);
		}
		else{
			return null;
		}
	}
	// findMax number 2
	private KeyComparable findMax(TreeNode myRoot){
		TreeNode current = myRoot;
		while(current.right != null){
			current = current.right;
		}
		return current.data;
	}
	//ShowTree number 1
	public String showTree(){
		return showTree(_root, 1);
	}
	//ShowTree number 2, uses a reverse order traversal
	private String showTree(TreeNode myRoot, int myLevel){
		String result = "";
		if(myRoot == null){
			result += "";
		}
		else{
			result += showTree(myRoot.right, myLevel + 1);
			for(int j = 0; j < myLevel; j++){
				result += "\t";
			}
			result += myRoot.data.toStringKey();
			result += showTree(myRoot.left, myLevel + 1);
		}
		return result;
	}
	//Normal toString method number 1
	public String toString(){
		return toString(_root);
	}
	//Normal toString method number 2, returns display in order
	private String toString(TreeNode myRoot){
		if(myRoot == null){ 
			return "";
		}
		else{
			String result = "";
			result += toString(myRoot.left);
			result += myRoot.data;
			result += toString(myRoot.right);
			return result;
		}
	}
	//getSize method number 1
	public int getSize(){
		return getSize(_root);
	}
	//getSize method number 2, total number of nodes
	private int getSize(TreeNode myRoot) {
        int count = 0;
        if (myRoot != null) {
            count++;
            count += getSize(myRoot.left);
            count += getSize(myRoot.right);
        }
        return count;
    }
	//returns the height of the tree
	public int getHeight(){
		return getHeight(_root);
	}
	private int getHeight(TreeNode myRoot) {
        if (myRoot == null) {
            return 0;
        } 
        else {
            return 1 + Math.max(getHeight(myRoot.left), getHeight(myRoot.right));
        }
    }
	/**
	Computes the sum of the levels of the 
	nodes in the tree
	*/
	public double getAverageLevel(){
		return getAverageLevel(_root, 1) / getSize();
	}
	private double getAverageLevel(TreeNode myRoot, double myLevel){
		if(myRoot == null){
			return 0;
		}
		else{
			return myLevel + getAverageLevel(myRoot.left, myLevel + 1) +
					getAverageLevel(myRoot.right, myLevel + 1);
		}
	}
}