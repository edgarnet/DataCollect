import java.util.Scanner;
import java.awt.Desktop;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;

/**
 * 
 * @author Edgar Perez
 * @version 2.01
 * Welcome to DataCollect! This program is designed to take input as a 
 * dataset from a file and present the user with useful statistics.
 * DataCollect also evaluates Z-Scores and prints the analysis to a file,
 * both of which are determined by the user.
 *
 */
public class DataCollectDriver {
	
	public static void main(String[] args) throws IOException{
		DataCollect examine = new DataCollect(); //Object for DataCollect methods.
		String file = "PerezEdgar_HW_Final_Prog_Features.txt"; //Documentation/manual for program.
		Scanner console = new Scanner(System.in); //Scanner object for user input.
		Scanner input = null; //Scanner object for file input.
		PrintWriter output = null; //PrintWriter output for file output.
		boolean complete = false; //Boolean for while loop.
		
		//testStatistics(); //Variable tracing and testing function.
		
		System.out.println("Welcome to DataCollect. Making sense of data.");
		System.out.println("Would you like to read the manual? (yes/no): ");
		String option = console.next(); //User input for first option.
		if(option.equals("yes") && Desktop.isDesktopSupported()){
			try{ //Opens the manual given user types "yes" and the Desktop class is supported.
				Desktop.getDesktop().open(new File(file)); //Object for Desktop and File classes.
			}
			catch(IOException e){
				System.out.println(e.getMessage()); //Prints error message for IOException.
			}
		}
		else if(option.equals("yes") && !Desktop.isDesktopSupported()){
			//Prints error message if the Desktop class is not supported.
			System.out.println("Sorry, it seems the file cannot be opened.");
		}
		
		while(!complete){
			System.out.print("Please enter the filename of your dataset: ");
			String inputFile = console.next();
			//The user's source for the dataset is passed into the Scanner object for file input.
			try{
				input = new Scanner(new File(inputFile));
			}
			catch(IOException e){
				System.out.println(e.getMessage());
			}
			ArrayList<Double> array = examine.array(input); //Creates an array based on file input. 
			System.out.println(examine.consoleOutput(array)); //Prints a statistical analysis.
			System.out.println(examine.cvOutput(array)); //Prints the level of variability of the user's dataset.
			examine.zScore(console, array); //Asks user for a number to determine the Z-Score of such input.
			System.out.print("\nEnter the filename you would like to save this collection in: ");
			String outputFile = console.next();
			//The user's choice for and output file is passed into the PrintWriter Object.
			try{
				output = new PrintWriter(outputFile);
			}
			catch(IOException e){
				System.out.println(e.getMessage());
			}
			//Prints the DataCollect analysis to the user's choice of file.
			examine.fileOutput(output, array);
			System.out.print("Would you like to examine another dataset? (yes/no): ");
			//Allows the user to input another dataset or exit the program.
			if(console.next().equals("no")){
				System.out.println("Saving your data.\nGoodbye!");
				complete = true;
				System.exit(0);
			}
			else{
				System.out.println();
			}
		}
	}
	
	public static void testStatistics() throws IOException{ //Tests the output of DataCollect and Collection methods.
		DataCollect test = new DataCollect(); //Object for DataCollect.
		Calculation check = new Calculation(); //Object for Calculation.
		Scanner input = null;
		//Scanner/File object for input based on sample dataset.
		try{
			input = new Scanner(new File("collect.txt"));
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		//Test for DataCollect.array();
		ArrayList<Double> data = test.array(input);
		//Test for Calculation.mean();
		System.out.println("mean: "+check.mean(data));
		//Test for Calculation.median();
		System.out.println("median: "+check.median(data));
		//Test for Calculation.range();
		System.out.println("range: "+check.range(data));
		//Test for Calculation.variance();
		System.out.println("variance: "+check.variance(data));
		//Test for Calculation.standardDev();
		System.out.println("standard deviation: "+check.standardDev(data));
		//Test for Calculation.coefVar();
		System.out.println("C.V.: "+check.coefVar(data));
		//Test for DataCollect.arrayOutput();
		test.arrayOutput(data);
		//Test for DataCollect.consoleOutput();
		test.consoleOutput(data);
		//Test for DataCollect.cvOutput();
		test.cvOutput(data);
	}
}
