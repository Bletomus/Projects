package chapter_2;

import java.util.Scanner;

public class FeetToMeters 
{

	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		double feet = 0;
		double meter = 0;
		System.out.println("please enter in some feet:");
		feet= input.nextDouble();
		meter = feet / 3.28;
		System.out.printf("Your feet equal to %.4f meters\n",meter);
				
	}

}
