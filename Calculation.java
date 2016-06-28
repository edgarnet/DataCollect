import java.util.ArrayList;

/**
 * 
 * @author Edgar Perez
 * @version 2.01
 * The Calculation class determines the mean, median, range, variance, standard deviation,
 * and the Coefficient of Variance of an ArrayList. The methods in this classes are
 * intended to perform a descriptive statistical analysis of the data in the 'adaptive' array;  
 *
 */
public class Calculation {
	
	public double mean(ArrayList<Double> list){ //Returns the Mean of a given ArrayList.
		double mean = 0; //Double to be returned.
		double sum = 0; //Stores the sum of all values in the ArrayList.
		double count = list.size(); //The total number of indexes in the ArrayList.
		for(int i = 0; i < count; i++){
			sum += list.get(i); //Every value of the ArrayList adds to the sum.
		}
		mean = sum/count; //The calculation for the Mean.
		return mean;
	}
	
	public double median(ArrayList<Double> list){ //Returns the Median of a given ArrayList.
		int length = list.size(); //The total number of indexes in the ArrayList.
		int index = (int) Math.ceil(length/2); //The calculation of the Median index (truncated)(indexes/2).
		double median = list.get(index); //Stores the value associated with the Median index.
		return median;
	}
	
	public double range(ArrayList<Double> list){ //Returns the Range of a given ArrayList.
		int length = list.size(); 
		double maximum = 0; //Stores the maximum value. 
		double minimum = 0; //Stores the minimum value.
		double range = 0; //Double to be returned.
		for(int i = 0; i < length; i++){
			if(list.get(i) > maximum) {
				maximum = list.get(i); //Every value larger than the next becomes the maximum.
			}
			if(list.get(i) < minimum){
				minimum = list.get(i); //Every value smaller than the next becomes the minimum.
			}
		}
		range = maximum - minimum; //The calculation for the range.
		return range;
	}
	
	public double variance(ArrayList<Double> list){ //Returns the Variance of a given ArrayList.
		int length = list.size();
		double sum = 0; //The sum of all possible calculations.
		double variance = 0; //Double to be returned.
		double mean = mean(list); //Stores the Mean of the ArrayList.
		for(int i = 0; i < length; i++){
			sum += ((list.get(i)-mean)*(list.get(i)-mean)); //The calculation for the variance of each value.
		}
		variance = sum/length; //The total calculation for the variance.
		return variance;
	}
	
	public double standardDev(ArrayList<Double> list){ //Returns the Standard Deviation of a given ArrayList.
		double standardDeviation = Math.sqrt(variance(list)); //The calculation, simply the square root of the variance.
		return standardDeviation;
	}
	
	public double coefVar(ArrayList<Double> list){ //Returns the Coefficient of Variance of a given ArrayList.
		double coefficientVariance = Math.abs((standardDev(list)/mean(list))); //The calculation, simply the ratio between Std. Dev. and Mean.
		return coefficientVariance;
	}
	
}
