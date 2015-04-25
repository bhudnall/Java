/* Program #5 Brian Hudnall- This program allows the user to create a train.
Components of the train include the engine and train cars, which the user
will be able to create as well. */

import java.util.ArrayList;
import java.util.Scanner;

public class Program5{
	
	public static Scanner keyboard;
	
	public static void main(String[] args){
		
		int answer = 0;
		double carMaxLoad = 0;
		Train train = null;
		Engine engine = null;
		Contents content = null;
		Contents[] contentArray = null;
		keyboard = new Scanner(System.in);
		
		System.out.println("You are able to make a Train and " +
				"calculate specific numbers related to the train\nusing this program. " +
				"After creating a train you will be able to create" +
				"\nspecific cars that the Train can carry. Along with " +
				"cars you will be asked\nspecifics regarding the Train " +
				"engine and carrying\ncapacity of individual cars.");
		
		carMaxLoad = maxCarLoad();
		engine = makeEngine();
		train = makeTrain(engine);
		contentArray = makeContent();
		do{
			answer = trainMenu();
			switch(answer){
				case 1:
					//Make and add new car to train
					content = contentChoice(contentArray);
					FreightCar freightCar = makeCar(carMaxLoad, content);
					train.addCar(freightCar);
					break;
				case 2:
					/*Display complete description of the 
					characteristics of the train, engine, and cars
					without weight and value */
					System.out.println("\n\nTrain: " + train);
					break;
				case 3:
					/*Display brief summary of each car
					including ID, weight, and value
					also notify if any car weight is
					greater than the pulling capacity of the
					engine */
					train.displayCarInfo(carMaxLoad);
					break;
				case 4:
					//Display total weight, number of cars, 
					//and value of train
					train.displayNums();
					break;
				case 5:
					//Make a new train
					engine = makeEngine();
					train = makeTrain(engine); 
					break;
				case 6:
					//Exit program
					break;
				default:
					System.out.println("\nPlease enter a menu option.");
			}
		}while(answer != 6);
		System.out.println("Goodbye");
	}
	/* maxCarLoad - gathers the maximum load a car can be */
	public static double maxCarLoad(){
		
		double carMaxLoad = 0;
		
		do{
			System.out.print("\nWhat is the maximum weight " +
					"of a single car?: ");
			carMaxLoad = keyboard.nextDouble();
			if(carMaxLoad < 0){
				System.out.println("\nError: enter a positive number");
			}
		}while(carMaxLoad < 0);
		return carMaxLoad;
	}
	/* makeCar - used by the addCar method to make
	 a car and then past the made car to the addCar 
	 method */
	public static FreightCar makeCar(double carMaxLoad, Contents content){
		
		double loadFactor = 0, baseFrameWeight;
		String owner;
		int id;
		Container container = makeContainer();
		
		System.out.print("\nWhat percentage of the container is full?: ");
		loadFactor = keyboard.nextDouble();
		System.out.print("\nWho is the freight car owner?: ");
		owner = keyboard.next();
		System.out.print("What is the freight car id?: ");
		id = keyboard.nextInt();
		System.out.print("What is the car base frame weight?: ");
		baseFrameWeight = keyboard.nextDouble();
		FreightCar freightCar = new FreightCar(owner, id, 
				baseFrameWeight, container, content, loadFactor);
		
		return freightCar;
	}
	/* makeTrain - creates a train using the engine
	 that passes as a parameter */
	public static Train makeTrain(Engine engine){
		
		String engineerName;
		Train train = null;
		System.out.println("\nAnswer the following" +
				" questions to create a train.");
		System.out.print("\nWhat is the name of the engineer?: ");
		engineerName = keyboard.next();// Train
		train = new Train(engineerName, engine); //Train
		
		return train;
	}
	/* trainMenu - shows the menu you can use for
	the train object */
	public static int trainMenu(){
		
		int answer = 0;
		do{
			System.out.println("\nPlease select from one of the following" +
					" options: " +
					"\n\t1) Add car" +
					"\n\t2) Display characteristics of Train, Engine, and Cars" +
					"\n\t3) Display a brief summary of each car" +
					"\n\t4) Display total weight and value of the train" +
					"\n\t5) Start a new train" +
					"\n\t6) Exit the program"); 
			answer = keyboard.nextInt();
			if (answer < 0 || answer > 6){
				System.out.println("\nPlease enter an appropriate choice");
			}
		}while(answer < 0 || answer > 6);
		return answer;
	}
	/* makeContainer - creates the container for each train car.
	 The method asks the user for the shape it would like the 
	 Container to be along with the thickness and density.*/
	public static Container makeContainer(){
		
		char containerType;
		double thickness, density;
		Container returnContainer = null;
		
		System.out.println("\nAnswer the following questions " +
				"to create the container of the train car.");
		do{
			System.out.println("\nWhat type of container does the new car have?: " +
					"\n\ta) Rectangle" +
					"\n\tb) Cylinder" +
					"\n\tc) Trapezoid");
			containerType = keyboard.next().charAt(0);
			System.out.print("What is the thickness?: ");
			thickness = keyboard.nextDouble();
			System.out.print("What is the density?: ");
			density = keyboard.nextDouble();
			if (containerType == 'a' || containerType == 'A'){
				returnContainer = makeRectangle(thickness, density);
			}
			else if (containerType == 'b' || containerType == 'B'){
				returnContainer = makeCylinder(thickness, density);
			}
			else if (containerType == 'c' || containerType == 'C'){
				returnContainer = makeTrapezoid(thickness, density);
			}
			else{
				System.out.println("\nERROR: please enter a menu option");
			}
		}while(containerType != 'a' && containerType != 'A' && 
			   containerType != 'b' && containerType != 'B' && 
			   containerType != 'c' && containerType != 'C');
		
		return returnContainer;
	}
	/* RectangularBox - creates the RectangularBox type of
	 the Container. */
	public static RectangularBox makeRectangle(double thickness, 
			double density){
		
		double height, width, length;
		
		System.out.print("What is the height?: ");
		height = keyboard.nextDouble();
		System.out.print("What is the width?: ");
		width = keyboard.nextDouble();
		System.out.print("What is the length?: ");
		length = keyboard.nextDouble();
		RectangularBox rectangle = new RectangularBox(thickness, density,
				height, width, length);
		
		return rectangle;	
	}
	/* Cylinder - creates the Cylinder type of the Container */
	public static Cylinder makeCylinder(double thickness, double density){
		
		double radius, height;
		System.out.print("What is the radius?: ");
		radius = keyboard.nextDouble();
		System.out.print("What is the height?: " );
		height = keyboard.nextDouble();
		Cylinder cylinder = new Cylinder(thickness, density, radius, height);
		
		return cylinder;
	}
	/* TrapezoidalBox - creates the trapezoid type Container */
	public static TrapezoidalBox makeTrapezoid(double thickness, 
			double density){
		
		double height, upperLength, lowerLength, width;
		
		System.out.print("What is the height?: ");
		height = keyboard.nextDouble();
		System.out.print("What is the upper length?: ");
		upperLength = keyboard.nextDouble();
		System.out.print("What is the lower length?: ");
		lowerLength = keyboard.nextDouble();
		System.out.print("What is the width?: ");
		width = keyboard.nextDouble();
		
		TrapezoidalBox trapezoid = new TrapezoidalBox(thickness, 
				density, height, upperLength, lowerLength, width);
		
		return trapezoid;
	}
	/* contentChoice - returns the contents from
	 the given array */
	public static Contents contentChoice(Contents[] content){
		int answer = 0;
		
		System.out.println("\nAnswer the following questions regarding the " +
				"contents of the train car container.");
		System.out.println("\nWhat is the content of the car?: ");
		for(int i = 0; i < 5; i++){
			System.out.println("\t" + (i + 1)  + ") " + 
					content[i].getName() + " Density:" + 
					content[i].getDensity() + " Value:" + 
					content[i].getValue());
		}
		answer = keyboard.nextInt();
		return content[answer - 1];	
	}
	/* makeContent - creates an array of the different 
	content options. */
	public static Contents[] makeContent(){
		
		Contents[] contents = new Contents[5];
		Contents content1 = new Contents("Oil", 55, 7.85);
		Contents content2 = new Contents("Coal", 69, 50);
		Contents content3 = new Contents("Soybeans", 47, 2.72);
		Contents content4 = new Contents("Linseed, meal", 32, 0.07);
		Contents content5 = new Contents("Oats", 27, 1.30);
		contents[0] = content1;
		contents[1] = content2;
		contents[2] = content3;
		contents[3] = content4;
		contents[4] = content5;
	
		return contents;
	}
	/* makeEngine - creates the engine for the Train
	object and passes the engine back to the main class */
	public static Engine makeEngine(){
		
		String owner;
		int id;
		double weightBaseFrame, pullingCapacity;
		
		System.out.println("\nAnswer the following questions to " +
				"create the engine.");
		System.out.print("\nWho is the owner of the engine: ");
		owner = keyboard.next();
		System.out.print("What is the engine id: ");
		id = keyboard.nextInt();
		System.out.print("What is the base frame weight?: ");
		weightBaseFrame = keyboard.nextDouble();
		System.out.print("What is the maximum weight the engine can pull in lbs?: ");
		pullingCapacity = keyboard.nextDouble();
		Engine engine = new Engine(owner, id, 
				weightBaseFrame, pullingCapacity);//Engine
		
		return engine;
	}
}

class Train{
	
	private ArrayList<FreightCar> _trainList;
	private Engine _engine;
	// the name of the engineer
	private String _nameEngineer;
	
	/* Train - the train class constructor */
	public Train(String name, Engine engine){
		
		_trainList = new ArrayList<FreightCar>();
		_nameEngineer = name;
		_engine = engine;
	}
	/* addCar - adds a class to the train list */
	public void addCar(FreightCar car){
		
		_trainList.add(car);	
	}
	/* deleteCar - deletes a car from the train list */
	public void deleteCar(int id){
		
		for(FreightCar c: _trainList){
			if(c.getId() == id){
				_trainList.remove(c);
			}
		}
	}
	/* changeLoadFactor - changes the load factor
	 of a specific car within the list */
	public void changeLoadFactor(int id, double loadFactor){
		
		for(FreightCar c: _trainList){
			if(c.getId() == id){
				c.changeLoadFactor(loadFactor);
			}
		}
	}
	/* displayNums - displays the total number of cars,
	the total weight, and the total value of the Train. */
	public void displayNums(){
		
		System.out.println("\nThe total number " +
				"of cars is: " + _trainList.size());
		System.out.println("\nThe total weight " +
				"of the Train is: lbs" + getTotalWeight());
		System.out.println("\nThe total value " +
				"of the Train is: $" + getTotalValue());
	}
	/* getTotalWeight - calculates the total weight
	 of the train */
	public double getTotalWeight(){
		
		double totalWeight = 0;
		
		for(FreightCar c: _trainList){
			totalWeight += c.totalWeight();
		}
		totalWeight += _engine.getWeight();
		return totalWeight;
	}
	/* getTotalWeight - calculates the total
	 value of the train. */
	public double getTotalValue(){
		
		double totalValue = 0;
		
		for(FreightCar c: _trainList){
			totalValue += c.totalValue();
		}
		return totalValue;	
	}
	/* toString - prints the details of each
	car in the list */
	public String toString(){
		
		String carSummary = "Engineer name: " + 
				_nameEngineer;
		
		for (FreightCar car : _trainList){
			carSummary += "\n" + car;
		}
		carSummary += "\n" + _engine.toString();
		return carSummary;
	}
	/* Display a brief summary of each car identifying ID and
	 describing its weight and value. notify if any car weight is
	 greater than the pulling capacity of the
	 engine*/ 
	public void displayCarInfo(double carMaxLoad){

		for(FreightCar car: _trainList){
			System.out.println("\nCar id: " + car.getId() +
							   " Car weight: " + car.totalWeight() +
							   " Car value: $" + car.totalValue());
			if(car.totalWeight() > carMaxLoad){
				System.out.println("This car has a weight greater" +
						" than the maximum load allowed for a single car.");
			}
		}
	}
	/* getName - returns the name of the engineer */
	public String getName(){
		
		return _nameEngineer;
	}
}

abstract class RollingStock{
	
	//owner: B&O, Reading, Pennsylvania, or Shortline
	private String _owner;
	private int _id;
	private double _weightBaseFrame;
	
	/* RollingStock - constructor */
	public RollingStock(String owner, int id, double 
			weightBaseFrame){
		_owner = owner;
		_id = id;
		_weightBaseFrame = weightBaseFrame;
	}
	/* getId - Id accessor */
	public int getId(){
		
		return _id;
	}
	/* getOwner - owner accessor */
	public String getOwner(){
		
		return _owner;
	}
	/* getWeight - weight accessor */
	public double getWeight(){
		
		return _weightBaseFrame;
	}
	/* toString - returns data members */
	public String toString(){
		
		return "\nCar Owner:" + _owner +
			   "\nCar Id:" + _id +
			   "\nCar Base Frame Weight:" +_weightBaseFrame;
	}
}
class FreightCar extends RollingStock{
	
	// What percentage of the container is full
	private double _loadFactor;
	private Container _container;
	private Contents _content;
	
	/* FreightCar - constructor */
	public FreightCar(String owner, int id, double weightBaseFrame, 
			Container container, Contents content, double loadFactor){
		super(owner, id, weightBaseFrame);
		_loadFactor = loadFactor;
		_container = container;
		_content = content;
	}
	/* getLoadFactor - loadFactor accessor. Load factor
	is what percentage of the container is full. */
	public double getLoadFactor(){
		
		// What percentage of the container is full
		return _loadFactor;
	}
	/* changeLoadFactor - load factor manipulator */
	public void changeLoadFactor(double loadFactor){
		
		_loadFactor = loadFactor;
	}
	// Calculate content weight
	private double calcContentWeight(){
		return _content.getDensity() * _container.calcInteriorVolume()
				* (_loadFactor / 100);
	}
	/* totalWeight - calculates and returns the total
	 weight of the freightCar*/
	public double totalWeight(){
		
		return (calcContentWeight() + 
				_container.calcWallWeight() + getWeight());
	}
	/* totalValue - calculates and returns the
	total value of the freightCar */
	public double totalValue(){
		
		return (calcContentWeight() * _content.getValue());
		
	}
	/* toString - returns the data members */
	public String toString(){
		
		return super.toString() + 
				"\nLoad Factor(% of container full): " + _loadFactor +
				"\n\nContainer: " + _container +
				"\n\nContent: " + _content;
	}
}

class Engine extends RollingStock{
	
	// Maximum weight the engine can pull
	private double _pullingCapacity;
	
	// Engine - constructor 
	public Engine(String owner, int id, double weightBaseFrame,
			double pullingCapacity){
		
		super(owner, id, weightBaseFrame);
		_pullingCapacity = pullingCapacity;
			
	}
	// Pulling capacity accessor
	public double getPullingCapacity(){
		return _pullingCapacity;
	}
	// toString - returns the data members 
	public String toString(){
	
		return super.toString() +
			"\nEngine Pulling Capacity: " + _pullingCapacity;
	}
}
class Contents{
	
	private String _name;
	private double _density;
	private double _value;
	
	/* Contents - constructor */
	public Contents(String name, double density, double value){
		
		_name = name;
		_density = density;
		_value = value;
	}
	/* getName - name accessor */
	public String getName(){
		
		return _name;
	}
	/* getDensity - density accessor */
	public double getDensity(){
		
		return _density;
	}
	/* getValue - value accessor */
	public double getValue(){
		
		return _value;
	}
	/* toString - returns the class data members */
	public String toString(){
		
		return "\n" + _name +
			   "\nDensity (pounds per cubic inch): " + _density + "lb" +
			   "\nValue (dollars per pount): $" + _value;
	}
}

abstract class Container{
	
	private double _thickness;
	private double _density;
	
	/* Container - constructor */
	public Container(double thickness, double density){
		
		_thickness = thickness;
		_density = density;
	}
	/* getThickness - thickness accessor */
	public double getThickness(){
		
		return _thickness;
	}
	/* getDensity - density accessor */
	public double getDensity(){
		
		return _density;
	}
	abstract public double calcInteriorVolume();
	abstract public double calcExteriorVolume();
	
	/* calcWallWeight - calculate the wall weight */
	public double calcWallWeight(){
		
		return (calcExteriorVolume() - calcInteriorVolume()) * _density;
	}
	/* toString - return Container data members */
	public String toString(){
		
		return "\nThickness: " + _thickness +
				"\nDensity: " + _density;
	}
}

class RectangularBox extends Container{
	
	private double _height;
	private double _width;
	private double _length;
	
	/* RectangularBox - constructor */
	public RectangularBox(double thickness, double density, double height,
					double width, double length){
		
		super(thickness, density);
		_height = height;
		_width = width;
		_length = length;
	}
	/* getHeight - height accessor */
	public double getHeight(){
		
		return _height;
	}
	/* getWidth - width accessor */
	public double getWidth(){
		
		return _width;
	}
	/* calcExtoriorVolume - calculates the exterior
	 volume of the Container */
	public double calcExteriorVolume(){
		
		return _height * _width * _length;
	}
	/* calcInteriorVolume - calculates the interior
	volume of the Container */
	public double calcInteriorVolume(){
		
		return (_length - (2 * getThickness())) *
			   (_width - (2 * getThickness())) *
			   (_height - (2 * getThickness()));
	}
	/* toString - returns the data members of the
	Container */
	public String toString(){
		
		return super.toString() +
				"\nHeight: " + _height +
				"\nWidth: " + _width +
				"\nLength: " + _length +
				"\nShape: Rectangle";
	}
}
class Cylinder extends Container{
	
	private double _radius;
	private double _height;
	
	/* Cylinder - constructor */
	public Cylinder(double thickness, double density, 
			double radius, double height){
		super(thickness, density);
		_radius = radius;
		_height = height;
	}
	/* calcExteriorVolume - calculates the exterior
	 volume of the Container */
	public double calcExteriorVolume(){
		
		return Math.PI * (Math.pow(_radius,  2) * _height);
	}
	/* calcInteriorVolume - calculates the interior
	 volume of the Container */
	public double calcInteriorVolume(){
		
		return Math.PI * Math.pow((_radius - getThickness()), 2) *
				(_height - (2 * getThickness()));
	}
	/* getRadius - radius accessor */
	public double getRadius(){
		
		return _radius;
	}
	/* getHeight - height accessor */
	public double getHeight(){
		
		return _height;
	}
	/* toString - returns the Container data members */
	public String toString(){
		
		return super.toString() +
				"\nRadius: " + _radius +
				"\nHeight: " + _height +
				"\nShape: Cylinder";
	}
}
class TrapezoidalBox extends Container{
	
	private double _height;
	private double _upperLength;
	private double _lowerLength;
	private double _width;
	
	/* TrapezoidalBox - constructor */
	public TrapezoidalBox(double thickness, double density,
						double height, double upperLength,
						double lowerLength, double width){
		super(thickness, density);
		_height = height;
		_upperLength = upperLength;
		_lowerLength = lowerLength;
		_width = width;
	}
	/* getHeight - height accessor */
	public double getHeight(){
		
		return _height;
	}
	/* getUpperLength - upper length accessor */
	public double getUpperLength(){
		
		return _upperLength;
	}
	/* getLowerLength - lower length accessor */
	public double getLowerLength(){
		
		return _lowerLength;
	}
	/* getWidth - width accessor */
	public double getWidth(){
		
		return _width;
	}
	/* calcExteriorVolume - returns the exterior volume
	 calculation for the Container */
	public double calcExteriorVolume(){
		
		return (.5 * (_upperLength + _lowerLength)) *
				_width * _height;
	}
	/* calcInteriorVolume - returns the interior volume
	 calculation for the Container */
	public double calcInteriorVolume(){
		
		return .5 * (_upperLength - ((2 * getThickness()) +
				+ _lowerLength - (2 * getThickness())) * (_width -
				(2 * getThickness()) * (_height - getThickness())));
	}
	public String toString(){
		
		return super.toString() + 
			"\nHeight: " + _height +
			"\nUpper length: " + _upperLength +
			"\nLower length: " + _lowerLength +
			"\nWidth: " + _width +
			"\nShape: Trapezoid";
	}
}