/* Program #3 Brian Hudnall- This program reads a data file that
contains 4 fields and generates an array of records. After reading
the file the program then prompts the user to either display the 
file, display a record within the file, display a cumulative value 
based on data from the file or display a histogram. */

import java.util.Scanner;
import java.io.*;

class CountryInfo {
	
	public String _country;
	public String _officialLanguage;
	public int _population;
	public int _grossDomProd;
}
public class Program3{
	
	public static Scanner keyboard;
	public static final int MAX = 100;
	
	public static void main(String[] args){
		
		int count = 0;
		int choice = 0;
		keyboard = new Scanner(System.in);
		CountryInfo[] countries = new CountryInfo[MAX];
		boolean stillWorking = true;
		count = readFile(countries);
		
		System.out.println("This program reads a data file that contains 4 fields " +
				"\nand generates an array of records. After reading the file " +
				"\nthe program will then ask you to either display the file, " +
				"\ndisplay a record within the file, display a cumulative value" + 
				"\nbased on data from the file or display a histogram.");
		
		// The following distributes the choice
		// to the different subprograms
		if (count > 0) {
			do{
				choice = collectChoice(choice);
				switch(choice){
					case 1:
						displayFile(countries, count);
						break;
					case 2:
						displayRecord(countries, count);
						break;
					case 3:
						displayCumulativeValue(countries, count);
						break;
					case 4:
						histogram(countries, count);
						break;
					case 5:
						stillWorking = false;
						break;
				}
			}while(stillWorking);
		}
		System.out.println("\nHave a nice day.\n\n");
	}
	/* The following subprogram reads the data file
	 and the generates the different records into
	 the array and then returns the number
	 of records in the array */
	public static int readFile(CountryInfo[] countries){
		
		String fileName = "Data3.txt";
		int count = 0;
		
		try{
			File file = new File(fileName);
			Scanner inputStream = new Scanner(file);
			do{
				countries[count] = new CountryInfo();
				countries[count]._country = inputStream.next();
				countries[count]._officialLanguage = inputStream.next();
				countries[count]._population = inputStream.nextInt();
				countries[count]._grossDomProd = inputStream.nextInt();
				count++;
			}while(countries[count - 1]._population != 0);
			count--;
		}
		catch (IOException ioe) {
			System.out.println("File access failure");
			count = 0;
		}
		return count;
	}
	/* The following subprogram displays the menu
	 and then collects a choice which is then
	 returns to the menu */
	private static int collectChoice(int choice) {
		
		do {
			System.out.println("\nEnter the number corresponding to your " +
					"choice: \n\t 1) Display the file \n\t 2) Display" +
					" a record \n\t 3) Display a cumulative record \n" +
					"\t 4) Display a histogram of the GDP per capita distribution\n\t    " +
					"based on decade ratio values\n\t 5) Quit");
			choice = keyboard.nextInt();
			if (choice < 1 || choice > 5){
				System.out.println("\nPlease enter one of the number options.");
			}	
		}while (choice < 1 || choice > 5);
		
		return choice;
	}
	/* The following subprogram displays the entire
	 file */
	public static void displayFile(CountryInfo[] countries, 
			int count){
		
		int counter = 0;
		String enter = null;
		
		System.out.println("COUNTRY\t\tLANGUAGE\tPOPULATION\t" +
				"GDP Per Capita");
		
		for (int i = 0; i < count; i++){
			System.out.printf("%-16s%-13s%14d%10d\n", countries[i]._country,
					countries[i]._officialLanguage, countries[i]._population,
					countries[i]._grossDomProd);
			counter++;
			if(counter == 14){
				System.out.println("Enter the word 'enter' to continue: ");
				enter = keyboard.next();
				counter = 0;
			}
		}
	}
	/* The following subprogram asked the user for a specific data point
	 and then displays the information related to that specific
	 data point */
	public static void displayRecord(CountryInfo[] countries, int count){
		
		boolean foundRecord = false;
		int recordIndex = 0;
		
		System.out.print("\nWhat country are you looking for?: ");
		String record = keyboard.next();
		for (int i = 0; i < count; i++){
			if (record.equalsIgnoreCase(countries[i]._country)){
				recordIndex = i;
				foundRecord = true;
			}
		}
		if (foundRecord){
			System.out.println("\nCOUNTRY\t\tLANGUAGE\tPOPULATION\t" +
					"GDP Per Capita");
			System.out.printf("%-16s%-10s%17d%10d\n", 
					countries[recordIndex]._country,
					countries[recordIndex]._officialLanguage, 
					countries[recordIndex]._population,
					countries[recordIndex]._grossDomProd);
		}
		else{
			System.out.println("\nThat country is not in the file.");
		}
	}
	/* The following subprogram finds an average based
	 specific data points that are collected from the user */
	public static void displayCumulativeValue(CountryInfo[] countries,
			int count){
		
		double max = 0, min = 0,cumulValue = 0, average = 0;
		int countAver = 0;
		
		do{
			System.out.print("\nWhat is the maximum GDP per capita value?: ");
			max = keyboard.nextInt();
			System.out.println("\nWhat is the minimum GDP per capita value?:");
			min = keyboard.nextInt();
			if (max < 0 || min < 0) {
				System.out.println("\nPlease enter positive values");
			}
			else if (max < min){
				System.out.println("\nPlease enter a minimum value that is" +
						"\nless than your maximum value.");
			}
			else{
				for(int i = 0; i < count; i++){
					if (countries[i]._grossDomProd >= min &&
							countries[i]._grossDomProd <= max){
						cumulValue += countries[i]._population;	
						countAver++;
					}
				}
				average = cumulValue / countAver;
				if (countAver > 0){
					System.out.printf("\nThe average value for the population" +
							"\nof countries that have GDP per capita between" +
							"\n%.2f and %.2f is %.2f", min, max, average);
					System.out.println();
				}
				else{
					System.out.printf("\nThere are no GDP per capita values" +
							"\nof countries that have GDP per capita between" +
							"\n%.2f and %.2f.", min, max);
					System.out.println();
				}
			}
		}while(max < 0 || min < 0 || max < min);		
	}
	/* The following subprogram displays a histogram
	 based on one of the fields included in the data
	 file. */
	public static void histogram(CountryInfo[] countries, int count){
		
		int[] decadeCount = new int[10];
		int decadeRange = 0, decadeCounter = 0;
		double min = findMin(countries, count);
		double max = findMax(countries, count);
		decadeCounter = (int)min;
		decadeRange = (int)Math.round((max - min) / 10);
		populateArray(countries, decadeCount, count, decadeRange);
		System.out.println("GDP decade values : Decade range count");
		for(int b = 0; b < 10; b++){
			System.out.printf("%15d\t%8d", decadeCounter, decadeCount[b]);
			System.out.print(" ");
			decadeCounter += decadeRange;
			for (int f = 0; f < decadeCount[b]; f++){
				System.out.print("*");
			}
			System.out.println();
		}
	}
	/*The following method populates the decade array*/
	private static void populateArray(CountryInfo[] countries,
				int[] decadeCount, int count, int decadeRange){
		
		double min = findMin(countries, count); 
		int result = 0;
		for (int z = 0; z < count; z++){
			result = (int)(countries[z]._grossDomProd - min)/decadeRange;
			decadeCount[result]++;
		}
	}
	/*The following method finds the minimum in the decade array*/
	private static double findMin(CountryInfo[] countries, int count){
		
		double min = countries[0]._grossDomProd; 
		for (int i = 0; i < count; i++){
			if (countries[i]._grossDomProd < min){
				min = countries[i]._grossDomProd;
			}
		}
		return min;
	}
	/*The following method finds the maximum in the decade array*/
	private static double findMax(CountryInfo[] countries, int count){
		
		double max = countries[0]._grossDomProd; 
		for (int i = 0; i < count; i++){
			if (countries[i]._grossDomProd > max){
				max = countries[i]._grossDomProd;
			}
		}
		return max;
	}
}
