package chapter_five;
import java.util.*;

//program to Count positive and negative numbers and compute the average of numbers as well as the total
public class AverageOfProsAndCons {

	public static void main(String[] args)
	{
		System.out.println("Please enter in integers and enter 0 when you wish to exit and display results.");
		int value = 0;
		int pros = 0;
		int cons = 0;
		int total = 0;
		double avg = 0;
		Scanner scanner = new Scanner(System.in);
		double count = 0;
		do
		{
			System.out.println("Enter in a interger: ");
			value = scanner.nextInt();
			
			if(value > 0)
			{
				count++;
				pros++;
				total+= value;
				avg = total / count;
			}
			else if(value < 0)
			{
				count++;
				cons++;
				total+= value;
				avg = total / count;
			}
		
		}while(value != 0);
		
		System.out.printf("Avg: %.2f\nTotal: %d\nPositives: %d\nNegatives: %d\n",avg, total,pros, cons);
	}

}
