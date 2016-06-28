import java.util.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * 
 * @author Edgar Perez
 * @version 2.01
 * The DataCollect class organizes data from input by Scanner into an ArrayList.
 * The methods of this class also present the user with results from the Calculation class,
 * along with the array itself and the ability to test Z-Score's of the user's choosing.
 * 
 */
public class DataCollect {
	
	private Calculation compute = new Calculation(); //Object for Calculation methods.
	
	public ArrayList<Double> array(Scanner input){ //Returns an ArrayList based on the given Scanner object.
		ArrayList<Double> data = new ArrayList<Double>(); //ArrayList to be returned.
		while(input.hasNextDouble()){
			data.add(input.nextDouble()); //Sets the values of the ArrayList to all real numbers in the input stream.
		}
		return data; 
	}
	
	public void arrayOutput(ArrayList<Double> list){ //Prints the values of the given ArrayList.
		for(int i = 0; i < list.size(); i++){
				System.out.print(list.get(i)); //For every index of the ArrayList, each value prints in the next line. 
		}
	}
	
	public String consoleOutput(ArrayList<Double> list){ //Returns a String of a given ArrayList's statistical analysis.
		String output = String.format("\r\nThe Mean of the data was: %.3f"
			+"\r\nThe Median of the data was: %.3f"
			+"\r\nThe Range of the data was: %.3f"
			+"\r\nThe Variance of the data was: %.3f"
			+"\r\nThe Standard Deviation of the data was: %.3f"
			,compute.mean(list),compute.median(list),compute.range(list)
			,compute.variance(list),compute.standardDev(list));
		//Each line of the String presents the values for Mean, Median, Range, Variance, and Standard Deviation respectively.
		return output;
	}
	
	public String cvOutput(ArrayList<Double> list){ //Returns a String of a given ArrayList's variability.
		String output = ""; //String to be returned.
		if(compute.coefVar(list) < 1){
			//If the Coefficient of Variance (C.V.) is less than 1, the values are close to the Mean.
			output = "The data has a low level of variability.";
		}
		else{
			//If the C.V. is more than 1, the values could be very far or very close to the Mean.
			output = "The data has a high level of variability.";
		}
		return output;
	}
	
	public void fileOutput(PrintWriter output, ArrayList<Double> list){ //Prints the analysis using given PrinWriter object.
		output.println(consoleOutput(list)); //Prints the statistical analysis.
		output.println(cvOutput(list)); //Prints the ArrayList's variability.
		output.println("Your data below:");
		int count = 0; //Counter variable for indexes.
		for(int i = 0; i < list.size(); i++){
				count++;
				output.println(count+". "+list.get(i)); //Prints each value of the ArrayList preceded by its index.
		}
		output.println("DataCollect Version 2.01");
		output.close(); //Closes PrintWriter object.
	}
	
	public void zScore(Scanner console, ArrayList<Double> list){ //Prints the Z-Score of user's input.
		boolean complete = false; //Boolean for while loop.
		double zScore = 0; //Stores the result of the assessment.
		double value = 0; //Stores the user's input.
		while(!complete){
			System.out.print("\nEnter a value to calculate its Z-Score: ");
			value = console.nextDouble();
			zScore = (value - compute.mean(list))/compute.standardDev(list); //The assesment of the zScore: (input-mean)/Std.Dev.
			System.out.printf("%.3f is %.3f Standard Deviations from the Mean.\n",value,zScore); //Prints the result.
			System.out.print("Would you like to calculate another value's Z-Score? (yes/no): "); //Asks the user for another input to test.
			if(console.next().equals("no")){ 
				complete = true; //If the user chooses "no," the loop ends and the program continues.
			}
		}
	}
}
